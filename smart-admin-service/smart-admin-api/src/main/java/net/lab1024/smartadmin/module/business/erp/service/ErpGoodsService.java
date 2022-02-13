package net.lab1024.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.erp.dao.ErpGoodsDao;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpGoodsEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpGoodsExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpGoodsVO;
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
 * @date 2022-02-08 23:11:43
 * @since JDK1.8
 */
@Service
public class ErpGoodsService {

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
     * 分页查询
     *
     * @author matt
     * @date 2022-02-08 23:11:43
     */
    public ResponseDTO<PageResultDTO<ErpGoodsVO>> queryByPage(ErpGoodsQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ErpGoodsVO> voList = erpGoodsDao.queryByPage(page, queryDTO);
        PageResultDTO<ErpGoodsVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
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
