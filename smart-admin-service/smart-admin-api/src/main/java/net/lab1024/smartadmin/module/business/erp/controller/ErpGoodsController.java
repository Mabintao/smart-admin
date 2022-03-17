package net.lab1024.smartadmin.module.business.erp.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpGoodsExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpGoodsVO;
import net.lab1024.smartadmin.module.business.erp.service.ErpGoodsService;
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
 * @date 2022-02-08 23:11:43
 * @since JDK1.8
 */
@RestController
@Api(tags = {""})
public class ErpGoodsController extends BaseController {

    @Autowired
    private ErpGoodsService erpGoodsService;

    @ApiOperation(value = "分页查询", notes = "@author matt")
    @PostMapping("/erpGoods/page/query")
    public ResponseDTO<PageResultDTO<ErpGoodsVO>> queryByPage(@RequestBody ErpGoodsQueryDTO queryDTO) {
        return erpGoodsService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加", notes = "@author matt")
    @PostMapping("/erpGoods/add")
    public ResponseDTO<String> add(@RequestBody @Validated ErpGoodsAddDTO addTO) {
        return erpGoodsService.add(addTO);
    }

    @ApiOperation(value = "修改", notes = "@author matt")
    @PostMapping("/erpGoods/update")
    public ResponseDTO<String> update(@RequestBody @Validated ErpGoodsUpdateDTO updateDTO) {
        return erpGoodsService.update(updateDTO);
    }

    @ApiOperation(value = "批量删除", notes = "@author matt")
    @PostMapping("/erpGoods/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return erpGoodsService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author matt")
    @PostMapping("/erpGoods/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ErpGoodsExcelVO> erpGoodsList = erpGoodsService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ErpGoodsExcelVO.class, erpGoodsList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author matt")
    @PostMapping("/erpGoods/export/all")
    public void exportAll(@RequestBody @Validated ErpGoodsQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ErpGoodsExcelVO> erpGoodsList = erpGoodsService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ErpGoodsExcelVO.class, erpGoodsList);
        downloadExcel("", workbook, response);
    }

}
