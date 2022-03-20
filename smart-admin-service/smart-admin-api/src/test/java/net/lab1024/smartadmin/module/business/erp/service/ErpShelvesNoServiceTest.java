package net.lab1024.smartadmin.module.business.erp.service;

import net.lab1024.smartadmin.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ErpShelvesNoServiceTest extends BaseTest {
    @Autowired
    ErpShelfNoService service;

    @Test
    public void genShelvesNosTest() {
        service.genShelvesNos(3, 10);
    }


}