package net.lab1024.smartadmin.module.business.erp.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpuVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpSpuExcelVO;
import net.lab1024.smartadmin.module.business.erp.service.ErpSpuService;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
@RestController
@Api(tags = {""})
public class ErpSpuController extends BaseController {

    @Autowired
    private ErpSpuService erpSpuService;

    @ApiOperation(value = "分页查询",notes = "@author matt")
    @PostMapping("/erpSpu/page/query")
    public ResponseDTO<PageResultDTO<ErpSpuVO>> queryByPage(@RequestBody ErpSpuQueryDTO queryDTO) {
        return erpSpuService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加",notes = "@author matt")
    @PostMapping("/erpSpu/add")
    public ResponseDTO<String> add(@RequestBody @Validated ErpSpuAddDTO addTO){
        return erpSpuService.add(addTO);
    }

    @ApiOperation(value="修改",notes = "@author matt")
    @PostMapping("/erpSpu/update")
    public ResponseDTO<String> update(@RequestBody @Validated ErpSpuUpdateDTO updateDTO){
        return erpSpuService.update(updateDTO);
    }

    @ApiOperation(value="批量删除",notes = "@author matt")
    @PostMapping("/erpSpu/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return erpSpuService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author matt")
    @PostMapping("/erpSpu/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ErpSpuExcelVO> erpSpuList = erpSpuService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ErpSpuExcelVO.class, erpSpuList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author matt")
    @PostMapping("/erpSpu/export/all")
    public void exportAll(@RequestBody @Validated ErpSpuQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ErpSpuExcelVO> erpSpuList = erpSpuService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ErpSpuExcelVO.class, erpSpuList);
        downloadExcel("", workbook, response);
    }

}
