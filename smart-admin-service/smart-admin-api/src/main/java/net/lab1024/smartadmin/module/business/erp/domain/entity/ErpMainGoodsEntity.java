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
 * @date 2022-02-08 20:52:39
 * @since JDK1.8
 */
@Data
@TableName("t_erp_main_goods")
public class ErpMainGoodsEntity extends BaseEntityV2 {

    /**
     * 产品优势
     */
    private String advantages;

    /**
     * 货架层数
     */
    private Integer shelfLayers;

}
