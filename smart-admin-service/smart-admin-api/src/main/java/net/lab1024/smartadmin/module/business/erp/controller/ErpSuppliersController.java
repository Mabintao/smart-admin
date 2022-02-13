package net.lab1024.smartadmin.module.business.erp.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSuppliersAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSuppliersQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSuppliersUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSuppliersExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSuppliersVO;
import net.lab1024.smartadmin.module.business.erp.service.ErpSuppliersService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
@RestController
@Api(tags = {SwaggerTagConst.Group2.ERP_SUPPLIERS})
public class ErpSuppliersController extends BaseController {

    @Autowired
    private ErpSuppliersService erpSuppliersService;

    @ApiOperation(value = "分页查询供应商表", notes = "@author matt")
    @PostMapping("/erpSuppliers/page/query")
    public ResponseDTO<PageResultDTO<ErpSuppliersVO>> queryByPage(@RequestBody ErpSuppliersQueryDTO queryDTO) {
        return erpSuppliersService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加供应商表", notes = "@author matt")
    @PostMapping("/erpSuppliers/add")
    public ResponseDTO<String> add(@RequestBody @Validated ErpSuppliersAddDTO addTO) {
        return erpSuppliersService.add(addTO);
    }

    @ApiOperation(value = "修改供应商表", notes = "@author matt")
    @PostMapping("/erpSuppliers/update")
    public ResponseDTO<String> update(@RequestBody @Validated ErpSuppliersUpdateDTO updateDTO) {
        return erpSuppliersService.update(updateDTO);
    }

    @ApiOperation(value = "批量删除供应商表", notes = "@author matt")
    @PostMapping("/erpSuppliers/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return erpSuppliersService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author matt")
    @PostMapping("/erpSuppliers/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ErpSuppliersExcelVO> erpSuppliersList = erpSuppliersService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("供应商表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ErpSuppliersExcelVO.class, erpSuppliersList);
        downloadExcel("供应商表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author matt")
    @PostMapping("/erpSuppliers/export/all")
    public void exportAll(@RequestBody @Validated ErpSuppliersQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ErpSuppliersExcelVO> erpSuppliersList = erpSuppliersService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("供应商表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ErpSuppliersExcelVO.class, erpSuppliersList);
        downloadExcel("供应商表", workbook, response);
    }

}
