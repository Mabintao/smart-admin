package net.lab1024.smartadmin.module.business.erp.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ErpGoodsAddDTO {
    @ApiModelProperty("主商品信息")
    private ErpSpuAddDTO spu;

    @NotEmpty(message = "商品规格必填")
    @ApiModelProperty("商品规格")
    private List<ErpSpecAddDTO> specs;
}
