package net.lab1024.smartadmin.module.business.erp.service;

import net.lab1024.smartadmin.BaseTest;
import net.lab1024.smartadmin.module.business.erp.domain.dto.*;
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

    @Test
    void editGoods() {
        ErpGoodsUpdateDTO updateDTO = new ErpGoodsUpdateDTO();

        ErpSpuUpdateDTO spu = new ErpSpuUpdateDTO();
        spu.setAdvantages("产品很有优势");
        spu.setName("复古手账记事便条纸留言记事8款");
        spu.setPic_url("https://cbu01.alicdn.com/img/ibank/O1CN018J16ke1ZlfxPQPTOP_!!2828383235-0-cib.jpg");
        spu.setId("647703661455");
        spu.setUrl("https://detail.1688.com/offer/647703661455.html");
        spu.setShelfLayers(3);

        List<ErpSkuUpdateDTO> skus = new ArrayList<>();
        ErpSkuUpdateDTO update1 = new ErpSkuUpdateDTO();
        update1.setId("647703661455-1");
        update1.setCoefficient(50);
        update1.setStock(30);
        update1.setWeight(100);
        update1.setPurchasePrice(100);
        skus.add(update1);

        ErpSkuUpdateDTO update2 = new ErpSkuUpdateDTO();
        update2.setId("647703661455-3");
        update2.setCoefficient(50);
        update2.setStock(30);
        update2.setWeight(100);
        update2.setPurchasePrice(100);
        skus.add(update2);

        updateDTO.setSpu(spu);
        updateDTO.setSkus(skus);

        erpGoodsService.editGoods(updateDTO);

    }
}