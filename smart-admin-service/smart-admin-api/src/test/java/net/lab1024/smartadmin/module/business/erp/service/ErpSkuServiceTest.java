package net.lab1024.smartadmin.module.business.erp.service;

import net.lab1024.smartadmin.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ErpSkuServiceTest extends BaseTest {
    @Autowired
    ErpSkuService erpSkuService;

    @Test
    void increaseStore() {
        erpSkuService.increaseStock("647703661455-1", 10);
    }

    @Test
    void decreaseStore() {
        erpSkuService.decreaseStock("647703661455-1", 10);
    }
}