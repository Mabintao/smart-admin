package net.lab1024.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.erp.dao.ErpSuppliersDao;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSuppliersAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSuppliersQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSuppliersUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpSuppliersEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSuppliersExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSuppliersVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [ 供应商表 ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c)  小马ERPInc. All rights reserved.
 * @date 2022-01-28 21:22:36
 * @since JDK1.8
 */
@Service
public class ErpSuppliersService {

    @Autowired
    private ErpSuppliersDao erpSuppliersDao;

    /**
     * 根据id查询
     */
    public ErpSuppliersEntity getById(String id) {
        return erpSuppliersDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author matt
     * @date 2022-01-28 21:22:36
     */
    public ResponseDTO<PageResultDTO<ErpSuppliersVO>> queryByPage(ErpSuppliersQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ErpSuppliersVO> voList = erpSuppliersDao.queryByPage(page, queryDTO);
        PageResultDTO<ErpSuppliersVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author matt
     * @date 2022-01-28 21:22:36
     */
    public ResponseDTO<String> add(ErpSuppliersAddDTO addDTO) {
        ErpSuppliersEntity entity = SmartBeanUtil.copy(addDTO, ErpSuppliersEntity.class);
        erpSuppliersDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     *
     * @author matt
     * @date 2022-01-28 21:22:36
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ErpSuppliersUpdateDTO updateDTO) {
        ErpSuppliersEntity entity = SmartBeanUtil.copy(updateDTO, ErpSuppliersEntity.class);
        erpSuppliersDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author matt
     * @date 2022-01-28 21:22:36
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        erpSuppliersDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author matt
     * @date 2022-01-28 21:22:36
     */
    public List<ErpSuppliersExcelVO> queryAllExportData(ErpSuppliersQueryDTO queryDTO) {
        return erpSuppliersDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author matt
     * @date 2022-01-28 21:22:36
     */
    public List<ErpSuppliersExcelVO> queryBatchExportData(List<Long> idList) {
        return erpSuppliersDao.queryBatchExportData(idList);
    }
}
