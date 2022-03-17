package net.lab1024.smartadmin.module.business.erp.service;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpGoodsVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSkuVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpecVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpuVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        PageResultDTO<ErpGoodsVO> ret = new PageResultDTO<>();
        ret.setPageNum(spuPageResult.getPageNum());
        ret.setPages(spuPageResult.getPages());
        ret.setPageSize(spuPageResult.getPageSize());
        ret.setTotal(spuPageResult.getTotal());
        ret.setList(goodList);

        return ResponseDTO.succData(ret);
    }

}
