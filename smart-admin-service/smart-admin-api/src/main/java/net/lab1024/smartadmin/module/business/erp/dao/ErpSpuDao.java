package net.lab1024.smartadmin.module.business.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpSpuEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpuVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpuExcelVO;
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
 * @date 2022-02-13 18:39:02
 * @since JDK1.8
 */
@Mapper
@Component
public interface ErpSpuDao extends BaseMapper<ErpSpuEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ErpSpuVO
    */
    IPage<ErpSpuVO> queryByPage(Page page, @Param("queryDTO") ErpSpuQueryDTO queryDTO);

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
    List<ErpSpuExcelVO> queryAllExportData(@Param("queryDTO")ErpSpuQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ErpSpuExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
}
