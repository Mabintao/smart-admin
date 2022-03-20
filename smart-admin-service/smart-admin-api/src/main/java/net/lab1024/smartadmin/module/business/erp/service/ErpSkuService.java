package net.lab1024.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.exception.SmartBusinessException;
import net.lab1024.smartadmin.module.business.erp.dao.ErpSkuDao;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSkuAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSkuUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpSkuEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSkuVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c)  小马ERPInc. All rights reserved.
 * @date 2022-02-13 18:40:06
 * @since JDK1.8
 */
@Service
@Slf4j
public class ErpSkuService {

    @Autowired
    private ErpSkuDao erpSkuDao;

    @Autowired
    private ErpShelfNoService erpShelfNoService;

    @Autowired
    private ErpSkuService erpSkuService;

    /**
     * 根据id查询
     */
    public ErpSkuEntity getById(String id) {
        return erpSkuDao.selectById(id);
    }


    /**
     * 根据主商品Id查询
     *
     * @param spuIds
     * @return
     */
    public List<ErpSkuVO> getBySpuIds(List<String> spuIds) {
        List<ErpSkuEntity> result = getEntityBySpuIds(spuIds);
        return SmartBeanUtil.copyList(result, ErpSkuVO.class);
    }

    private List<ErpSkuEntity> getEntityBySpuIds(List<String> spuIds) {
        LambdaQueryWrapper<ErpSkuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ErpSkuEntity::getSpuId, spuIds);
        List<ErpSkuEntity> result = erpSkuDao.selectList(queryWrapper);
        return result;
    }

    /**
     * 批量新增
     *
     * @param addDTOList
     * @return
     */
    public ResponseDTO<String> batchInsert(List<ErpSkuAddDTO> addDTOList) {
        List<ErpSkuEntity> skuList = SmartBeanUtil.copyList(addDTOList, ErpSkuEntity.class);
        erpSkuDao.insertBatchSomeColumn(skuList);

        return ResponseDTO.succ();
    }

    /**
     * 批量更新
     *
     * @param updateList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> updateBatch(List<ErpSkuUpdateDTO> updateList) {
        List<ErpSkuEntity> updateEntities = SmartBeanUtil.copyList(updateList, ErpSkuEntity.class);
        erpSkuDao.batchUpdate(updateEntities);

        return ResponseDTO.succ();
    }


    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> updateShelfNum(String spuId, Integer layer) {
        List<ErpSkuEntity> skus = getEntityBySpuIds(Arrays.asList(spuId));
        List<Long> nos = erpShelfNoService.genShelvesNos(layer, skus.size());
        for (ErpSkuEntity en : skus) {
            int index = skus.indexOf(en);
            en.setOrderNum(String.format("%s-%d", layer, nos.get(index)));
        }

        erpSkuDao.batchUpdateOrderNum(skus);
        return ResponseDTO.succ();
    }

    public ResponseDTO<String> increaseStock(String skuId, Integer num) {
        return updateStockBase(skuId, num, true);
    }

    public ResponseDTO<String> decreaseStock(String skuId, Integer num) {
        return updateStockBase(skuId, num * -1, true);
    }

    public ResponseDTO<String> updateStockBase(String skuId, Integer num, Boolean needRedo) {
        ErpSkuEntity sku = getById(skuId);
        if (num < 0 && sku.getStock() < num * -1) {
            throw new SmartBusinessException("库存更新失败，当前库存不足");
        }

        UpdateWrapper<ErpSkuEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("stock", sku.getStock())
                .eq("id", sku.getId())
                .set("stock", sku.getStock() + num);

        int update = erpSkuDao.update(null, updateWrapper);
        if (update != 1 && needRedo) {
            erpSkuService.updateStockBase(skuId, num, false);
        }

        return ResponseDTO.succ();
    }
}
