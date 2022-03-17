package net.lab1024.smartadmin.module.business.erp.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

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
public class ErpSpecExcelVO {
    @Excel(name = "ID")
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

    @Excel(name = "属性")
    private String attr;

    @Excel(name = "属性值")
    private String attrValue;


}
