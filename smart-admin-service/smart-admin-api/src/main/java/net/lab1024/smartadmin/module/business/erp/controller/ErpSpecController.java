package net.lab1024.smartadmin.module.business.erp.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSkuAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSkuQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSkuUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSkuExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSkuVO;
import net.lab1024.smartadmin.module.business.erp.service.ErpSkuService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
@RestController
@Api(tags = {""})
public class ErpSpecController extends BaseController {

    @Autowired
    private ErpSkuService erpSpecService;

    @ApiOperation(value = "分页查询", notes = "@author matt")
    @PostMapping("/erpSpec/page/query")
    public ResponseDTO<PageResultDTO<ErpSkuVO>> queryByPage(@RequestBody ErpSkuQueryDTO queryDTO) {
        return erpSpecService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加", notes = "@author matt")
    @PostMapping("/erpSpec/add")
    public ResponseDTO<String> add(@RequestBody @Validated ErpSkuAddDTO addTO) {
        return erpSpecService.add(addTO);
    }

    @ApiOperation(value = "修改", notes = "@author matt")
    @PostMapping("/erpSpec/update")
    public ResponseDTO<String> update(@RequestBody @Validated ErpSkuUpdateDTO updateDTO) {
        return erpSpecService.update(updateDTO);
    }

    @ApiOperation(value = "批量删除", notes = "@author matt")
    @PostMapping("/erpSpec/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return erpSpecService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author matt")
    @PostMapping("/erpSpec/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ErpSkuExcelVO> erpSpecList = erpSpecService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ErpSkuExcelVO.class, erpSpecList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author matt")
    @PostMapping("/erpSpec/export/all")
    public void exportAll(@RequestBody @Validated ErpSkuQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ErpSkuExcelVO> erpSpecList = erpSpecService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ErpSkuExcelVO.class, erpSpecList);
        downloadExcel("", workbook, response);
    }

}
