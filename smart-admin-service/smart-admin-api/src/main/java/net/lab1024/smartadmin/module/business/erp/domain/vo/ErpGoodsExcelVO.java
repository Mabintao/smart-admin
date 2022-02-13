package net.lab1024.smartadmin.module.business.erp.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;

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
public class ErpGoodsExcelVO {
    @Excel(name = "规格ID")
    private String id;

    @Excel(name = "创建人")
    private String createUser;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Excel(name = "更新人")
    private String updateUser;

    @Excel(name = "更新时间", format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Excel(name = "主商品编号")
    private String mainGoodsId;

    @Excel(name = "重量")
    private Integer weight;

    @Excel(name = "最小单位系数")
    private Integer coefficient;

    @Excel(name = "规格名称")
    private String name;

    @Excel(name = "库存（最小单位）")
    private Integer stock;

    @Excel(name = "当前状态")
    private Integer status;

    @Excel(name = "采购价")
    private Integer purchasePrice;

    @Excel(name = "货架排序号")
    private String order_num;


}
