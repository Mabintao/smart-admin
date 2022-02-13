package net.lab1024.smartadmin.module.business.erp.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * [ 供应商表 ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c) 小马ERPInc. All rights reserved.
 * @date 2022-01-28 21:22:36
 * @since JDK1.8
 */
@Data
public class ErpSuppliersVO {
    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("供应商名称")
    private String name;

    @ApiModelProperty("url")
    private String url;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty("创建用户")
    private String createUser;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新用户")
    private String updateUser;

}
