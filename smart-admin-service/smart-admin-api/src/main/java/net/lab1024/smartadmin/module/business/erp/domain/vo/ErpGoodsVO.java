package net.lab1024.smartadmin.module.business.erp.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c) 小马ERPInc. All rights reserved.
 * @date 2022-02-08 23:11:43
 * @since JDK1.8
 */
@Data
public class ErpGoodsVO {
    @ApiModelProperty("主商品信息")
    private ErpSpuVO spu;

    @ApiModelProperty("商品")
    private List<ErpSpecVO> specs;

    @ApiModelProperty("商品规格")
    private List<ErpSkuVO> skus;
}
