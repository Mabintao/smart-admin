package net.lab1024.smartadmin.module.business.erp.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.lab1024.smartadmin.common.domain.BaseEntityV2;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_erp_shelf_no")
public class ErpShelfNoEntity extends BaseEntityV2 {
    /**
     * 货架层
     */
    private Integer layer;

    /**
     * 货架编号
     */
    private Long no;

    /**
     * 显示编号
     */
    private String displayNo;
}
