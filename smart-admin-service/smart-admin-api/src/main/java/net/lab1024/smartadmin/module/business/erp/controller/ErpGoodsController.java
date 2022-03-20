package net.lab1024.smartadmin.module.business.erp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsUpdateDTO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpGoodsVO;
import net.lab1024.smartadmin.module.business.erp.service.ErpGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
@Api(tags = {SwaggerTagConst.Group2.ERP_GOODS})
public class ErpGoodsController extends BaseController {

    @Autowired
    private ErpGoodsService erpGoodsService;

    @ApiOperation(value = "商品列表查询")
    @NoNeedLogin
    @PostMapping("/erpGoods/page/query")
    public ResponseDTO<PageResultDTO<ErpGoodsVO>> queryByPage(@RequestBody ErpGoodsQueryDTO queryDTO) {
        return erpGoodsService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "商品查询")
    @NoNeedLogin
    @GetMapping("/erpGoods/queryById")
    public ResponseDTO<ErpGoodsVO> queryById(@RequestParam("商品ID") String id) {
        return erpGoodsService.queryById(id);
    }

    @ApiOperation(value = "商品添加")
    @NoNeedLogin
    @PostMapping("/erpGoods/add")
    public ResponseDTO<String> addGoods(@Valid @RequestBody ErpGoodsAddDTO addDTO) {
        return erpGoodsService.addGoods(addDTO);
    }


    @ApiOperation(value = "商品编辑")
    @NoNeedLogin
    @PostMapping("/erpGoods/edit")
    public ResponseDTO<String> editGoods(@RequestBody ErpGoodsUpdateDTO updateDTO) {
        return erpGoodsService.editGoods(updateDTO);
    }


}
