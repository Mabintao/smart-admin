package net.lab1024.smartadmin.module.business.erp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsQueryDTO;
import net.lab1024.smartadmin.module.business.erp.domain.vo.ErpGoodsVO;
import net.lab1024.smartadmin.module.business.erp.service.ErpGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "分页查询", notes = "@author matt")
    @NoNeedLogin
    @PostMapping("/erpGoods/page/query")
    public ResponseDTO<PageResultDTO<ErpGoodsVO>> queryByPage(@RequestBody ErpGoodsQueryDTO queryDTO) {
        return erpGoodsService.queryByPage(queryDTO);
    }
}
