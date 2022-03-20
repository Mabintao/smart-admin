package net.lab1024.smartadmin.module.business.erp.service;

import net.lab1024.smartadmin.common.constant.ResponseCodeConst;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.*;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpSpuEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpGoodsVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSkuVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpecVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpuVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartSpecUtil;
import net.lab1024.smartadmin.util.SmartStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c)  小马ERPInc. All rights reserved.
 * @date 2022-02-08 23:11:43
 * @since JDK1.8
 */
@Service
public class ErpGoodsService {

    // 商品规格
    @Autowired
    private ErpSpecService specService;

    // 主商品
    @Autowired
    private ErpSpuService spuService;

    // 子商品
    @Autowired
    private ErpSkuService skuService;

    // 货柜编号
    @Autowired
    private ErpShelfNoService erpShelfNoService;

    /**
     * 商品-分页查询
     *
     * @param queryDTO
     * @return
     */
    public ResponseDTO<PageResultDTO<ErpGoodsVO>> queryByPage(ErpGoodsQueryDTO queryDTO) {
        // 查询主商品信息
        ErpSpuQueryDTO spuQueryDTO = SmartBeanUtil.copy(queryDTO, ErpSpuQueryDTO.class);
        PageResultDTO<ErpSpuVO> spuPageResult = spuService.queryByPage(spuQueryDTO);

        // 构造返回数据，补充子商品信息以及规格信息
        PageResultDTO<ErpGoodsVO> ret = new PageResultDTO<>();
        ret.setPageNum(spuPageResult.getPageNum());
        ret.setPages(spuPageResult.getPages());
        ret.setPageSize(spuPageResult.getPageSize());
        ret.setTotal(spuPageResult.getTotal());

        if (spuPageResult.getList().isEmpty()) {
            ret.setList(new ArrayList<>());
            return ResponseDTO.succData(ret);
        }

        // 获取所有的商品ID，关联查询规格以及子商品
        List<String> spuIds = spuPageResult.getList().stream()
                .map(ErpSpuVO::getId)
                .collect(Collectors.toList());

        List<ErpSkuVO> skuList = skuService.getBySpuIds(spuIds);
        List<ErpSpecVO> specList = specService.getBySpuIds(spuIds);

        List<ErpGoodsVO> goodList = new ArrayList<>();
        for (ErpSpuVO spu : spuPageResult.getList()) {
            ErpGoodsVO good = new ErpGoodsVO();
            good.setSpu(spu);

            List<ErpSkuVO> curSkuList = skuList.stream()
                    .filter(p -> p.getSpuId().equals(spu.getId()))
                    .collect(Collectors.toList());
            good.setSkus(curSkuList);

            List<ErpSpecVO> curSpecList = specList.stream()
                    .filter(p -> p.getSpuId().equals(spu.getId()))
                    .collect(Collectors.toList());

            good.setSpecs(curSpecList);

            goodList.add(good);
        }

        // 构造返回数据，补充子商品信息以及规格信息
        ret.setList(goodList);
        return ResponseDTO.succData(ret);
    }

    /**
     * 单个商品查询
     *
     * @param id
     * @return
     */
    public ResponseDTO<ErpGoodsVO> queryById(String id) {
        ErpGoodsQueryDTO queryDTO = new ErpGoodsQueryDTO();
        queryDTO.setId(id);
        queryDTO.setPageNum(1);
        queryDTO.setPageSize(1);

        ResponseDTO<PageResultDTO<ErpGoodsVO>> result = queryByPage(queryDTO);
        List<ErpGoodsVO> list = result.getData().getList();
        if (list.isEmpty()) {
            return ResponseDTO.wrap(ResponseCodeConst.NOT_EXISTS, "无法获取商品信息");
        } else {
            return ResponseDTO.succData(list.get(0));
        }
    }

    /**
     * 新增商品
     *
     * @param addDTO
     * @return
     */
    @Transactional
    public ResponseDTO<String> addGoods(ErpGoodsAddDTO addDTO) {
        // 添加主商品信息
        spuService.add(addDTO.getSpu());

        // 添加属性信息
        for (ErpSpecAddDTO spec : addDTO.getSpecs()) {
            spec.setSpuId(addDTO.getSpu().getId());
            spec.setId(SmartStringUtil.genRandomId());
        }
        specService.batchInsert(addDTO.getSpecs());

        // 初始化商品
        List<ErpSkuAddDTO> skus = genSkus(addDTO);
        skuService.batchInsert(skus);

        return ResponseDTO.succ();
    }

    /**
     * 新增商品
     * 通过规格生成对应的单品信息
     *
     * @return
     */
    private List<ErpSkuAddDTO> genSkus(ErpGoodsAddDTO addDTO) {

        List<SpecDTO> specList = addDTO.getSpecs().stream()
                .map(p -> SmartBeanUtil.copy(p, SpecDTO.class))
                .collect(Collectors.toList());

        List<SpecCombineDTO> specCombines = SmartSpecUtil.specCombinations(specList);
        List<Long> shelfNos = erpShelfNoService.genShelvesNos(addDTO.getSpu().getShelfLayers(), specCombines.size());

        List<ErpSkuAddDTO> skus = specCombines.stream().map(p -> {
            int index = specCombines.indexOf(p);
            String orderNum = String.format("%s-%d", addDTO.getSpu().getShelfLayers(), shelfNos.get(index));
            return initSku(addDTO.getSpu().getId(), index, orderNum, p);
        }).collect(Collectors.toList());

        return skus;
    }

    private ErpSkuAddDTO initSku(String spuId, Integer index, String orderNum, SpecCombineDTO spec) {
        return ErpSkuAddDTO.builder()
                .id(String.format("%s-%d", spuId, index + 1))
                .spuId(spuId)
                .attr(SmartStringUtil.join(spec.getFormatAttrs(), ","))
                .attrIds(SmartStringUtil.join(spec.getIds(), ","))
                .orderNum(orderNum)
                .coefficient(0)
                .purchasePrice(0)
                .status(0)
                .stock(0)
                .weight(0)
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> editGoods(ErpGoodsUpdateDTO updateDTO) {
        ErpSpuEntity spu = spuService.getById(updateDTO.getSpu().getId());
        boolean needUpdateShelfNum = false;
        if (!spu.getShelfLayers().equals(updateDTO.getSpu().getShelfLayers())) {
            needUpdateShelfNum = true;
        }

        spuService.update(updateDTO.getSpu());
        skuService.updateBatch(updateDTO.getSkus());

        if (needUpdateShelfNum) {
            skuService.updateShelfNum(updateDTO.getSpu().getId(), updateDTO.getSpu().getShelfLayers());
        }

        return ResponseDTO.succ();
    }
}
