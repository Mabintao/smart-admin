package net.lab1024.smartadmin.module.business.erp;

import net.lab1024.smartadmin.BaseTest;
import net.lab1024.smartadmin.module.business.erp.domain.dto.ErpSpecAddDTO;
import net.lab1024.smartadmin.module.business.erp.service.ErpSpecService;
import net.lab1024.smartadmin.util.SmartStringUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BatchInsertTest extends BaseTest {
    @Autowired
    ErpSpecService erpSpecService;

    @Test
    public void batchInsert() {
        List<ErpSpecAddDTO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ErpSpecAddDTO add = new ErpSpecAddDTO();
            add.setSpuId(String.valueOf(i));
            add.setAttr("aaa");
            add.setAttrValue("bbb");

            list.add(add);
        }
        erpSpecService.batchInsert(list);
    }

    @Test
    public void randomIdTest() {
        System.out.println(SmartStringUtil.genRandomId());
    }

}
