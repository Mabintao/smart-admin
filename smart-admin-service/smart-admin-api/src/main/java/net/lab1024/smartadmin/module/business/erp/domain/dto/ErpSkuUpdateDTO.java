package net.lab1024.smartadmin.module.business.erp.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新 [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c) 2018 小马ERPInc. All rights reserved.
 * @date 2022-02-13 18:40:06
 * @since JDK1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ErpSkuUpdateDTO extends ErpSkuAddDTO {

    private String id;

}
