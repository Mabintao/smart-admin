package net.lab1024.smartadmin.module.business.erp.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新建 [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c) 2018 小马ERPInc. All rights reserved.
 * @date 2022-02-08 23:11:43
 * @since JDK1.8
 */
@Data
public class ErpGoodsAddDTO {
    @ApiModelProperty("主商品编号")
    private String mainGoodsId;

    @ApiModelProperty("重量")
    private Integer weight;

    @ApiModelProperty("最小单位系数")
    private Integer coefficient;

    @ApiModelProperty("规格名称")
    private String name;

    @ApiModelProperty("库存（最小单位）")
    private Integer stock;

    @ApiModelProperty("当前状态")
    private Integer status;

    @ApiModelProperty("采购价")
    private Integer purchasePrice;
}
