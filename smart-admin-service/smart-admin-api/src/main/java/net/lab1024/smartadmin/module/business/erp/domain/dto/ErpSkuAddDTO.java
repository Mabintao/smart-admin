package net.lab1024.smartadmin.module.business.erp.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

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
public class ErpSkuAddDTO {
    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("主商品编号")
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
