package net.lab1024.smartadmin.module.business.erp.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c) 小马ERPInc. All rights reserved.
 * @date 2022-02-08 20:52:39
 * @since JDK1.8
 */
@Data
public class ErpMainGoodsVO {

    @ApiModelProperty("1688ID")
    private String id;

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

    @ApiModelProperty("产品优势")
    private String advantages;

    @ApiModelProperty("货架层数")
    private Integer shelfLayers;

    @ApiModelProperty("规格商品信息")
    private List<ErpGoodsVO> goods;

}
