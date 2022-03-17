package net.lab1024.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.erp.dao.ErpGoodsDao;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpGoodsEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.*;
import net.lab1024.smartadmin.util.SmartBeanUtil;
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
    private ErpSpecService skuService;

    // 主商品
    @Autowired
    private ErpSpuService spuService;

    // 子商品
    @Autowired
    private ErpSkuService specService;

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

        List<ErpSkuVO> specList = specService.getBySpuIds(spuIds);
        List<ErpSpecVO> skuList = skuService.getBySpuIds(spuIds);

        List<ErpGoodsVO> goodList = new ArrayList<>();
        for (ErpSpuVO spu : spuPageResult.getList()) {
            ErpGoodsVO good = new ErpGoodsVO();
            good.setSpu(spu);

            List<ErpSkuVO> curSpecList = specList.stream()
                    .filter(p -> p.getSpuId().equals(spu.getId()))
                    .collect(Collectors.toList());
            good.setSpecs(curSpecList);

            List<ErpSpecVO> curSkuList = skuList.stream()
                    .filter(p -> p.getSpuId().equals(spu.getId()))
                    .collect(Collectors.toList());

            good.setSkus(curSkuList);
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


    @Autowired
    private ErpGoodsDao erpGoodsDao;

    /**
     * 根据id查询
     */
    public ErpGoodsEntity getById(String id) {
        return erpGoodsDao.selectById(id);
    }

    /**
     * 根据主商品ID查询
     *
     * @param ids
     * @return
     */
    public List<ErpGoodsVO> getByMainIds(List<String> ids) {
        LambdaQueryWrapper<ErpGoodsEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ErpGoodsEntity::getMainGoodsId, ids);
        List<ErpGoodsEntity> result = erpGoodsDao.selectList(queryWrapper);
        return SmartBeanUtil.copyList(result, ErpGoodsVO.class);
    }

    /**
     * 添加
     *
     * @author matt
     * @date 2022-02-08 23:11:43
     */
    public ResponseDTO<String> add(ErpGoodsAddDTO addDTO) {
        ErpGoodsEntity entity = SmartBeanUtil.copy(addDTO, ErpGoodsEntity.class);
        erpGoodsDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     *
     * @author matt
     * @date 2022-02-08 23:11:43
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ErpGoodsUpdateDTO updateDTO) {
        ErpGoodsEntity entity = SmartBeanUtil.copy(updateDTO, ErpGoodsEntity.class);
        erpGoodsDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author matt
     * @date 2022-02-08 23:11:43
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        erpGoodsDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author matt
     * @date 2022-02-08 23:11:43
     */
    public List<ErpGoodsExcelVO> queryAllExportData(ErpGoodsQueryDTO queryDTO) {
        return erpGoodsDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author matt
     * @date 2022-02-08 23:11:43
     */
    public List<ErpGoodsExcelVO> queryBatchExportData(List<Long> idList) {
        return erpGoodsDao.queryBatchExportData(idList);
    }
}
