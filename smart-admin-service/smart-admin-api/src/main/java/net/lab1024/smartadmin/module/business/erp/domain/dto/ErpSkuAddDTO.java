package net.lab1024.smartadmin.module.business.erp.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新建 [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c) 2018 小马ERPInc. All rights reserved.
 * @date 2022-02-13 18:40:06
 * @since JDK1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErpSkuAddDTO {
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("spuId")
    private String spuId;

    @ApiModelProperty("重量")
    private Integer weight;

    @ApiModelProperty("最小单位系数")
    private Integer coefficient;

    @ApiModelProperty("规格名称")
    private String attr;

    @ApiModelProperty("库存（最小单位）")
    private Integer stock;

    @ApiModelProperty("当前状态")
    private Integer status;

    @ApiModelProperty("采购价")
    private Integer purchasePrice;

    @ApiModelProperty("货架排序号")
    private String orderNum;

    @ApiModelProperty("规格属性ID，逗号分隔")
    private String attrIds;
}
