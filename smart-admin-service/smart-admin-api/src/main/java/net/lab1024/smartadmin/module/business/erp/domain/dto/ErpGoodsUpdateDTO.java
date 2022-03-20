package net.lab1024.smartadmin.module.business.erp.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ErpGoodsUpdateDTO {
    private ErpSpuUpdateDTO spu;
    private List<ErpSkuUpdateDTO> skus;
}
