package net.lab1024.smartadmin.module .business.erp.domain.dto;

        import net.lab1024.smartadmin.common.domain.PageParamDTO;
        import io.swagger.annotations.ApiModelProperty;
        import lombok.Data;
        import java.util.Date;
    
        /**
         * [  ]
         *
         * @author matt
         * @version 1.0
         * @company 小马ERP
         * @copyright (c)  小马ERPInc. All rights reserved.
         * @date 2022-02-13 18:39:29
         * @since JDK1.8
         */
        @Data
        public class ErpSkuQueryDTO extends PageParamDTO{
    
                        @ApiModelProperty("ID")
                private String id;
                    }
