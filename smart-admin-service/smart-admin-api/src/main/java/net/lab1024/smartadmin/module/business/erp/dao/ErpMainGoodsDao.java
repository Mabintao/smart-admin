package net.lab1024.smartadmin.module.business.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpMainGoodsQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpMainGoodsEntity;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpMainGoodsVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpMainGoodsExcelVO;
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
 * @date 2022-02-08 20:52:39
 * @since JDK1.8
 */
@Mapper
@Component
public interface ErpMainGoodsDao extends BaseMapper<ErpMainGoodsEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ErpMainGoodsVO
    */
    IPage<ErpMainGoodsVO> queryByPage(Page page, @Param("queryDTO") ErpMainGoodsQueryDTO queryDTO);

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
    List<ErpMainGoodsExcelVO> queryAllExportData(@Param("queryDTO")ErpMainGoodsQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ErpMainGoodsExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
}
