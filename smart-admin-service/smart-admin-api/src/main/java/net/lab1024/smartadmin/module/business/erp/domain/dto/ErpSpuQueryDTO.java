package net.lab1024.smartadmin.module.business.erp.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.lab1024.smartadmin.common.domain.PageParamDTO;

/**
 * [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c)  小马ERPInc. All rights reserved.
 * @date 2022-02-13 18:39:02
 * @since JDK1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ErpSpuQueryDTO extends PageParamDTO {

    @ApiModelProperty("1688ID")
    private String id;

    @ApiModelProperty("商品名称")
    private String name;
}
