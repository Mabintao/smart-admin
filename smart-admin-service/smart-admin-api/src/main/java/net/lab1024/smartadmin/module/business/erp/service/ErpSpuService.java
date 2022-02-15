package net.lab1024.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.erp.dao.ErpSpuDao;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpSpuEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpecVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpuExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpuVO;
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
 * @date 2022-02-13 18:39:02
 * @since JDK1.8
 */
@Service
public class ErpSpuService {

    @Autowired
    private ErpSpuDao erpSpuDao;

    @Autowired
    private ErpSpecService erpSpecService;

    /**
     * 根据id查询
     */
    public ErpSpuEntity getById(Long id) {
        return erpSpuDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @author matt
     * @date 2022-02-13 18:39:02
     */
    public ResponseDTO<PageResultDTO<ErpSpuVO>> queryByPage(ErpSpuQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ErpSpuVO> voList = erpSpuDao.queryByPage(page, queryDTO);

        // 补充规格商品的信息
        List<String> spuIds = voList.getRecords().stream()
                .map(ErpSpuVO::getId).collect(Collectors.toList());
        List<ErpSpecVO> specs = erpSpecService.getBySpuIds(spuIds);

        voList.getRecords().forEach(p -> {
            List<ErpSpecVO> curSpecs = specs.stream()
                    .filter(q -> q.getSpuId().equals(p.getId()))
                    .collect(Collectors.toList());

            p.setSpecs(curSpecs);
        });

        PageResultDTO<ErpSpuVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author matt
     * @date 2022-02-13 18:39:02
     */
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
