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
 * @date 2022-02-08 20:52:39
 * @since JDK1.8
 */
@Data
public class ErpMainGoodsAddDTO {

    @ApiModelProperty("产品优势")
    private String advantages;

    @ApiModelProperty("货架层数")
    private Integer shelfLayers;


}
