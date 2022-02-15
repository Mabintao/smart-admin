package net.lab1024.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.erp.dao.ErpSpecDao;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpecAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpecQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpecUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpSpecEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpecExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpecVO;
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
public class ErpSpecService {

    @Autowired
    private ErpSpecDao erpSpecDao;

    /**
     * 根据id查询
     */
    public ErpSpecEntity getById(Long id) {
        return erpSpecDao.selectById(id);
    }


    public List<ErpSpecVO> getBySpuIds(List<String> ids) {
        LambdaQueryWrapper<ErpSpecEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ErpSpecEntity::getSpuId, ids);
        List<ErpSpecEntity> result = erpSpecDao.selectList(queryWrapper);
        return SmartBeanUtil.copyList(result, ErpSpecVO.class);
    }

    /**
     * 分页查询
     *
     * @author matt
     * @date 2022-02-13 18:40:06
     */
    public ResponseDTO<PageResultDTO<ErpSpecVO>> queryByPage(ErpSpecQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ErpSpecVO> voList = erpSpecDao.queryByPage(page, queryDTO);
        PageResultDTO<ErpSpecVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author matt
     * @date 2022-02-13 18:40:06
     */
    public ResponseDTO<String> add(ErpSpecAddDTO addDTO) {
        ErpSpecEntity entity = SmartBeanUtil.copy(addDTO, ErpSpecEntity.class);
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
    public ResponseDTO<String> update(ErpSpecUpdateDTO updateDTO) {
        ErpSpecEntity entity = SmartBeanUtil.copy(updateDTO, ErpSpecEntity.class);
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
    public List<ErpSpecExcelVO> queryAllExportData(ErpSpecQueryDTO queryDTO) {
        return erpSpecDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author matt
     * @date 2022-02-13 18:40:06
     */
    public List<ErpSpecExcelVO> queryBatchExportData(List<Long> idList) {
        return erpSpecDao.queryBatchExportData(idList);
    }
}
