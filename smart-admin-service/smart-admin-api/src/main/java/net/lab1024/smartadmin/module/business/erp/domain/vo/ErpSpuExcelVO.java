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
 * @date  2022-02-13 18:39:02
 * @since JDK1.8
 */
@Data
public class ErpSpuExcelVO {
    @Excel(name = "1688ID")
    private String id;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "创建用户")
    private String createUser;

    @Excel(name = "更新时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Excel(name = "更新用户")
    private String updateUser;

    @Excel(name = "产品优势")
    private String advantages;

    @Excel(name = "货架层数")
    private Integer shelfLayers;



}
