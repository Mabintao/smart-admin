package net.lab1024.smartadmin.module.business.erp.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;

/**
 *  [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c) 小马ERPInc. All rights reserved.
 * @date  2022-02-13 18:40:06
 * @since JDK1.8
 */
@Data
public class ErpSpecExcelVO {
    @Excel(name = "规格ID")
    private String id;

    @Excel(name = "创建人")
    private String createUser;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "更新人")
    private String updateUser;

    @Excel(name = "更新时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Excel(name = "主商品编号")
    private String spuId;

    @Excel(name = "重量")
    private Integer weight;

    @Excel(name = "最小单位系数")
    private Integer coefficient;

    @Excel(name = "规格名称")
    private String attr;

    @Excel(name = "库存（最小单位）")
    private Integer stock;

    @Excel(name = "当前状态")
    private Integer status;

    @Excel(name = "采购价")
    private Integer purchasePrice;

    @Excel(name = "货架排序号")
    private String orderNum;

    @Excel(name = "规格属性ID，逗号分隔")
    private String attrIds;



}
