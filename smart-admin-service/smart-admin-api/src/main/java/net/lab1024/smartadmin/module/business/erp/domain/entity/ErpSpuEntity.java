package net.lab1024.smartadmin.module .business.erp.domain.entity;

        import com.baomidou.mybatisplus.annotation.TableName;
        import net.lab1024.smartadmin.common.domain.BaseEntityV2;
            import lombok.Data;

/**
 * [  ]
 *
 * @author matt
 * @version 1.0
 * @company 小马ERP
 * @copyright (c)  小马ERPInc. All rights reserved.
 * @date 2022-02-13 18:39:02
 * @since JDK1.8
 */
        @Data
        @TableName("t_erp_spu")
        public class ErpSpuEntity extends BaseEntityV2{
    
            
            
            
            
            
                        /**
                 * 产品优势
                 */
                private String advantages;
            
                        /**
                 * 货架层数
                 */
                private Integer shelfLayers;
            
        }
