package net.lab1024.smartadmin.module.business.erp.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 更新 [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c) 2018 小马ERPInc. All rights reserved.
 * @date 2022-02-13 18:40:06
 * @since JDK1.8
 */
@Data
public class ErpSkuUpdateDTO {
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("重量")
    private Integer weight;

    @ApiModelProperty("最小单位系数")
    private Integer coefficient;

    @ApiModelProperty("库存（最小单位）")
    private Integer stock;

    @ApiModelProperty("采购价/分")
    private Integer purchasePrice;
}
