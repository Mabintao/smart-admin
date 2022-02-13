package net.lab1024.smartadmin.module.business.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSuppliersQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpSuppliersEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSuppliersVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSuppliersExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
@Mapper
@Component
public interface ErpSuppliersDao extends BaseMapper<ErpSuppliersEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ErpSuppliersVO
    */
    IPage<ErpSuppliersVO> queryByPage(Page page, @Param("queryDTO") ErpSuppliersQueryDTO queryDTO);

    /**
     * 根据id删除
     * @param id
     * @return
    */
    void deleteById(@Param("id")Long id);

    /**
     * 根据id批量删除
     * @param idList
     * @return
    */
    void deleteByIdList(@Param("idList") List<Long> idList);

        /**
     * 查询所有导出数据
     * @param queryDTO
     * @return
     */
    List<ErpSuppliersExcelVO> queryAllExportData(@Param("queryDTO")ErpSuppliersQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ErpSuppliersExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
}
