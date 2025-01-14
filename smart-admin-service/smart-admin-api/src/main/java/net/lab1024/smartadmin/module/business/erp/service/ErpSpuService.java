package net.lab1024.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.erp.dao.ErpSpuDao;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpSpuEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpuExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpuVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartStringUtil;
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
 * @date 2022-02-13 18:39:02
 * @since JDK1.8
 */
@Service
public class ErpSpuService {

    @Autowired
    private ErpSpuDao erpSpuDao;

    /**
     * 根据id查询
     */
    public ErpSpuEntity getById(String id) {
        return erpSpuDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author matt
     * @date 2022-02-13 18:39:02
     */
    public PageResultDTO<ErpSpuVO> queryByPage(ErpSpuQueryDTO queryDTO) {
        Page<ErpSpuEntity> page = SmartPageUtil.convert2QueryPage(queryDTO);
        LambdaQueryWrapper<ErpSpuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SmartStringUtil.isNotEmpty(queryDTO.getId()), ErpSpuEntity::getId, queryDTO.getId());
        queryWrapper.like(SmartStringUtil.isNotEmpty(queryDTO.getName()), ErpSpuEntity::getName, queryDTO.getName());
        Page<ErpSpuEntity> pageInfo = erpSpuDao.selectPage(page, queryWrapper);

        return SmartPageUtil.convert2PageResult(pageInfo, pageInfo.getRecords(), ErpSpuVO.class);
    }

    /**
     * 添加
     *
     * @author matt
     * @date 2022-02-13 18:39:02
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(ErpSpuAddDTO addDTO) {
        ErpSpuEntity entity = SmartBeanUtil.copy(addDTO, ErpSpuEntity.class);
        erpSpuDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     *
     * @author matt
     * @date 2022-02-13 18:39:02
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ErpSpuUpdateDTO updateDTO) {
        ErpSpuEntity entity = SmartBeanUtil.copy(updateDTO, ErpSpuEntity.class);
        erpSpuDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author matt
     * @date 2022-02-13 18:39:02
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        erpSpuDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author matt
     * @date 2022-02-13 18:39:02
     */
    public List<ErpSpuExcelVO> queryAllExportData(ErpSpuQueryDTO queryDTO) {
        return erpSpuDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author matt
     * @date 2022-02-13 18:39:02
     */
    public List<ErpSpuExcelVO> queryBatchExportData(List<Long> idList) {
        return erpSpuDao.queryBatchExportData(idList);
    }
}
