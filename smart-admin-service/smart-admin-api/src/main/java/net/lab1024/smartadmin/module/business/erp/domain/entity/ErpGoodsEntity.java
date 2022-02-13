package net.lab1024.smartadmin.module.business.erp.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.lab1024.smartadmin.common.domain.BaseEntityV2;

/**
 * [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c)  小马ERPInc. All rights reserved.
 * @date 2022-02-08 23:11:43
 * @since JDK1.8
 */
@Data
@TableName("t_erp_goods")
public class ErpGoodsEntity extends BaseEntityV2 {
    
    /**
     * 主商品编号
     */
    private String mainGoodsId;

    /**
     * 重量
     */
    private Integer weight;

    /**
     * 最小单位系数
     */
    private Integer coefficient;

    /**
     * 规格名称
     */
    private String name;

    /**
     * 库存（最小单位）
     */
    private Integer stock;

    /**
     * 当前状态
     */
    private Integer status;

    /**
     * 采购价
     */
    private Integer purchasePrice;

    /**
     * 货架排序号
     */
    private String order_num;

}
