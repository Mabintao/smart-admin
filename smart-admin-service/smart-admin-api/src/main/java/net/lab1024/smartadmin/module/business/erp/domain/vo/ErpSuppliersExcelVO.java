package net.lab1024.smartadmin.module.business.erp.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
public class ErpSuppliersExcelVO {
    @Excel(name = "主键")
    private String id;

    @Excel(name = "name")
    private String name;

    @Excel(name = "url")
    private String url;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Excel(name = "创建用户")
    private String createUser;

    @Excel(name = "更新时间", format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Excel(name = "更新用户")
    private String updateUser;

}
