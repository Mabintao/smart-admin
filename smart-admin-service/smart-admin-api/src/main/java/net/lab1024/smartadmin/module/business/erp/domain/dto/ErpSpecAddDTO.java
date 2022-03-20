package net.lab1024.smartadmin.module.business.erp.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpSpecAddDTO {
    @ApiModelProperty("Id")
    private String id;

    @NotBlank(message = "规格名称必填")
    @ApiModelProperty("属性")
    private String attr;

    @NotBlank(message = "规格值必填")
    @ApiModelProperty("属性值")
    private String attrValue;

    @ApiModelProperty("主商品编号")
    private String spuId;
}
