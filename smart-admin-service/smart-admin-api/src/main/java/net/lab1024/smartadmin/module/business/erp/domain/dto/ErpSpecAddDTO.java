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
 * @date 2022-02-13 18:39:29
 * @since JDK1.8
 */
@Data
public class ErpSpecAddDTO {
    @ApiModelProperty("主商品编号")
    private String spuId;

    @ApiModelProperty("属性")
    private String attr;

    @ApiModelProperty("属性值")
    private String attrValue;
}
