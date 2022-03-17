package net.lab1024.smartadmin.module.business.erp.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c) 小马ERPInc. All rights reserved.
 * @date 2022-02-13 18:39:29
 * @since JDK1.8
 */
@Data
public class ErpSpecVO {
    @ApiModelProperty("spuId")
    private String spuId;

    @ApiModelProperty("属性列表，key为规格名，value为规格值")
    private Map<String, List<String>> attrs = new HashMap<>();
}
