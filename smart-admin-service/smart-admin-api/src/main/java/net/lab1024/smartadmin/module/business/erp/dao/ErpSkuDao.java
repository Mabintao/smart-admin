package net.lab1024.smartadmin.module.business.erp.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.mybatis.SmartBaseMapper;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSkuQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpSkuEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSkuExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSkuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
@Mapper
@Component
public interface ErpSkuDao extends SmartBaseMapper<ErpSkuEntity> {

    /**
     * 分页查询
     *
     * @param queryDTO
     * @return ErpSpecVO
     */
    IPage<ErpSkuVO> queryByPage(Page page, @Param("queryDTO") ErpSkuQueryDTO queryDTO);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    void deleteById(@Param("id") Long id);

    /**
     * 根据id批量删除
     *
     * @param idList
     * @return
     */
    void deleteByIdList(@Param("idList") List<Long> idList);

    /**
     * 查询所有导出数据
     *
     * @param queryDTO
     * @return
     */
    List<ErpSkuExcelVO> queryAllExportData(@Param("queryDTO") ErpSkuQueryDTO queryDTO);

    /**
     * 查询批量导出数据
     *
     * @param idList
     * @return
     */
    List<ErpSkuExcelVO> queryBatchExportData(@Param("idList") List<Long> idList);

    void batchUpdate(@Param("updateList") List<ErpSkuEntity> updateList);

    void batchUpdateOrderNum(@Param("updateList") List<ErpSkuEntity> updateList);
}
