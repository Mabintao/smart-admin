package net.lab1024.smartadmin.module.business.erp.service;

import net.lab1024.smartadmin.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ErpShelvesNoServiceTest extends BaseTest {
    @Autowired
    ErpShelfNoService service;

    @Test
    public void genShelvesNosTest() {
        List<Long> longs = service.genShelvesNos(3, 10);
        System.out.println(longs);
    }


}