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
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpMainGoodsAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpMainGoodsQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpMainGoodsUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpMainGoodsExcelVO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpMainGoodsVO;
import net.lab1024.smartadmin.module.business.erp.service.ErpMainGoodsService;
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
 * @date 2022-02-08 20:52:39
 * @since JDK1.8
 */
@RestController
@Api(tags = SwaggerTagConst.Group2.ERP_GOODS)
public class ErpMainGoodsController extends BaseController {

    @Autowired
    private ErpMainGoodsService erpMainGoodsService;

    @ApiOperation(value = "分页查询", notes = "@author matt")
    @PostMapping("/erpMainGoods/page/query")
    public ResponseDTO<PageResultDTO<ErpMainGoodsVO>> queryByPage(@RequestBody ErpMainGoodsQueryDTO queryDTO) {
        return erpMainGoodsService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加", notes = "@author matt")
    @PostMapping("/erpMainGoods/add")
    public ResponseDTO<String> add(@RequestBody @Validated ErpMainGoodsAddDTO addTO) {
        return erpMainGoodsService.add(addTO);
    }

    @ApiOperation(value = "修改", notes = "@author matt")
    @PostMapping("/erpMainGoods/update")
    public ResponseDTO<String> update(@RequestBody @Validated ErpMainGoodsUpdateDTO updateDTO) {
        return erpMainGoodsService.update(updateDTO);
    }

    @ApiOperation(value = "批量删除", notes = "@author matt")
    @PostMapping("/erpMainGoods/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return erpMainGoodsService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author matt")
    @PostMapping("/erpMainGoods/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ErpMainGoodsExcelVO> erpMainGoodsList = erpMainGoodsService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ErpMainGoodsExcelVO.class, erpMainGoodsList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author matt")
    @PostMapping("/erpMainGoods/export/all")
    public void exportAll(@RequestBody @Validated ErpMainGoodsQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ErpMainGoodsExcelVO> erpMainGoodsList = erpMainGoodsService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ErpMainGoodsExcelVO.class, erpMainGoodsList);
        downloadExcel("", workbook, response);
    }

}
