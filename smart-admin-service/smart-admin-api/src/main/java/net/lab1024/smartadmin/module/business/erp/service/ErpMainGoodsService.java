package net.lab1024.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.erp.dao.ErpMainGoodsDao;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpMainGoodsAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpMainGoodsQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpMainGoodsUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpMainGoodsEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpGoodsVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpMainGoodsExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpMainGoodsVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c)  小马ERPInc. All rights reserved.
 * @date 2022-02-08 20:52:39
 * @since JDK1.8
 */
@Service
public class ErpMainGoodsService {

    @Autowired
    private ErpMainGoodsDao erpMainGoodsDao;

    @Autowired
    private ErpGoodsService erpGoodsService;

    /**
     * 根据id查询
     */
    public ErpMainGoodsEntity getById(Long id) {
        return erpMainGoodsDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author matt
     * @date 2022-02-08 20:52:39
     */
    public ResponseDTO<PageResultDTO<ErpMainGoodsVO>> queryByPage(ErpMainGoodsQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ErpMainGoodsVO> voList = erpMainGoodsDao.queryByPage(page, queryDTO);

        // 补充规格商品的信息
        List<String> pageMainGoodIds = voList.getRecords().stream()
                .map(ErpMainGoodsVO::getId).collect(Collectors.toList());
        List<ErpGoodsVO> pageGoods = erpGoodsService.getByMainIds(pageMainGoodIds);

        voList.getRecords().forEach(p -> {
            List<ErpGoodsVO> curGoods = pageGoods.stream()
                    .filter(q -> q.getMainGoodsId().equals(p.getId()))
                    .collect(Collectors.toList());

            p.setGoods(curGoods);
        });

        PageResultDTO<ErpMainGoodsVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author matt
     * @date 2022-02-08 20:52:39
     */
    public ResponseDTO<String> add(ErpMainGoodsAddDTO addDTO) {
        ErpMainGoodsEntity entity = SmartBeanUtil.copy(addDTO, ErpMainGoodsEntity.class);
        erpMainGoodsDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     *
     * @author matt
     * @date 2022-02-08 20:52:39
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ErpMainGoodsUpdateDTO updateDTO) {
        ErpMainGoodsEntity entity = SmartBeanUtil.copy(updateDTO, ErpMainGoodsEntity.class);
        erpMainGoodsDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author matt
     * @date 2022-02-08 20:52:39
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        erpMainGoodsDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author matt
     * @date 2022-02-08 20:52:39
     */
    public List<ErpMainGoodsExcelVO> queryAllExportData(ErpMainGoodsQueryDTO queryDTO) {
        return erpMainGoodsDao.queryAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author matt
     * @date 2022-02-08 20:52:39
     */
    public List<ErpMainGoodsExcelVO> queryBatchExportData(List<Long> idList) {
        return erpMainGoodsDao.queryBatchExportData(idList);
    }
}
