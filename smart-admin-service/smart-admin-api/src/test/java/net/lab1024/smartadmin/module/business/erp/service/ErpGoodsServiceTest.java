package net.lab1024.smartadmin.module.business.erp.service;

import net.lab1024.smartadmin.BaseTest;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpGoodsAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpecAddDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpuAddDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

class ErpGoodsServiceTest extends BaseTest {

    @Autowired
    ErpGoodsService erpGoodsService;

    @Test
    void addGoods() {
        ErpGoodsAddDTO addDTO = new ErpGoodsAddDTO();

        ErpSpuAddDTO spu = new ErpSpuAddDTO();
        spu.setAdvantages("很棒的产品");
        spu.setName("美好的一天 染色季复古标签集 复古手账记事便条纸留言记事8款");
        spu.setPic_url("https://cbu01.alicdn.com/img/ibank/O1CN018J16ke1ZlfxPQPTOP_!!2828383235-0-cib.jpg");
        spu.setId("647703661455");
        spu.setUrl("https://detail.1688.com/offer/647703661455.html");
        spu.setShelfLayers(1);

        List<ErpSpecAddDTO> specs = new ArrayList<>();
        specs.add(ErpSpecAddDTO.builder().attr("颜色").attrValue("红色").build());
        specs.add(ErpSpecAddDTO.builder().attr("颜色").attrValue("绿色").build());
        specs.add(ErpSpecAddDTO.builder().attr("颜色").attrValue("蓝色").build());
        specs.add(ErpSpecAddDTO.builder().attr("颜色").attrValue("紫色").build());
        specs.add(ErpSpecAddDTO.builder().attr("款式").attrValue("复古").build());
        specs.add(ErpSpecAddDTO.builder().attr("款式").attrValue("简约").build());

        addDTO.setSpu(spu);
        addDTO.setSpecs(specs);

        erpGoodsService.addGoods(addDTO);
    }
}