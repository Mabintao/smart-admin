package net.lab1024.smartadmin.module.business.erp.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 新建 [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c) 2018 小马ERPInc. All rights reserved.
 * @date 2022-02-13 18:39:02
 * @since JDK1.8
 */
@Data
public class ErpSpuAddDTO {
    @NotBlank(message = "1688ID不能为空")
    @ApiModelProperty("1688ID")
    private String id;

    @ApiModelProperty("产品优势")
    private String advantages;

    @NotNull(message = "货架层数必填")
    @ApiModelProperty("货架层数")
    private Integer shelfLayers;

    @NotBlank(message = "商品名称必填")
    @ApiModelProperty("名称")
    private String name;

    @NotBlank(message = "商品链接必填")
    @ApiModelProperty("商品链接")
    private String url;

    @NotBlank(message = "商品主图地址必填")
    @ApiModelProperty("商品图片地址")
    private String pic_url;
}
