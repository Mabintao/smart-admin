package net.lab1024.smartadmin.module.business.erp.domain.dto;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c) 2018 小马ERPInc. All rights reserved.
 * @date  2022-02-13 18:39:29
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

    @ApiModelProperty("属性")
    private String attr;

    @ApiModelProperty("属性值")
    private String attrValue;


}
