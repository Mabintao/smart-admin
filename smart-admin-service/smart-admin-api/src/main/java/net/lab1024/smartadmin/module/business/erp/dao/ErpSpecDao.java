package net.lab1024.smartadmin.module.business.erp.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.mybatis.SmartBaseMapper;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpecQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpSpecEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpecExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpecVO;
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
 * @date 2022-02-13 18:39:29
 * @since JDK1.8
 */
@Mapper
@Component
public interface ErpSpecDao extends SmartBaseMapper<ErpSpecEntity> {

    /**
     * 分页查询
     *
     * @param queryDTO
     * @return ErpSkuVO
     */
    IPage<ErpSpecVO> queryByPage(Page page, @Param("queryDTO") ErpSpecQueryDTO queryDTO);

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
    List<ErpSpecExcelVO> queryAllExportData(@Param("queryDTO") ErpSpecQueryDTO queryDTO);

    /**
     * 查询批量导出数据
     *
     * @param idList
     * @return
     */
    List<ErpSpecExcelVO> queryBatchExportData(@Param("idList") List<Long> idList);
}
