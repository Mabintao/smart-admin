package net.lab1024.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.erp.dao.ErpSkuDao;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSkuAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSkuQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSkuUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpSkuEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSkuExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSkuVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ErpSkuService {

    @Autowired
    private ErpSkuDao erpSpecDao;

    /**
     * 根据id查询
     */
    public ErpSkuEntity getById(Long id) {
        return erpSpecDao.selectById(id);
    }


    public List<ErpSkuVO> getBySpuIds(List<String> ids) {
        LambdaQueryWrapper<ErpSkuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ErpSkuEntity::getSpuId, ids);
        List<ErpSkuEntity> result = erpSpecDao.selectList(queryWrapper);
        return SmartBeanUtil.copyList(result, ErpSkuVO.class);
    }

    /**
     * 分页查询
     *
     * @author matt
     * @date 2022-02-13 18:40:06
     */
    public ResponseDTO<PageResultDTO<ErpSkuVO>> queryByPage(ErpSkuQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ErpSkuVO> voList = erpSpecDao.queryByPage(page, queryDTO);
        PageResultDTO<ErpSkuVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author matt
     * @date 2022-02-13 18:40:06
     */
    public ResponseDTO<String> add(ErpSkuAddDTO addDTO) {
        ErpSkuEntity entity = SmartBeanUtil.copy(addDTO, ErpSkuEntity.class);
        erpSpecDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     *
     * @author matt
     * @date 2022-02-13 18:40:06
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ErpSkuUpdateDTO updateDTO) {
        ErpSkuEntity entity = SmartBeanUtil.copy(updateDTO, ErpSkuEntity.class);
        erpSpecDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author matt
     * @date 2022-02-13 18:40:06
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        erpSpecDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author matt
     * @date 2022-02-13 18:40:06
     */
    public List<ErpSkuExcelVO> queryAllExportData(ErpSkuQueryDTO queryDTO) {
        return erpSpecDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author matt
     * @date 2022-02-13 18:40:06
     */
    public List<ErpSkuExcelVO> queryBatchExportData(List<Long> idList) {
        return erpSpecDao.queryBatchExportData(idList);
    }
}
