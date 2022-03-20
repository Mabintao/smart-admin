package net.lab1024.smartadmin.module.business.erp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.lab1024.smartadmin.module.business.erp.dao.ErpShelfNoDao;
import net.lab1024.smartadmin.module.business.erp.domain.entity.ErpShelfNoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ErpShelfNoService {
    @Autowired
    private ErpShelfNoDao erpShelvesNoDao;

    public List<Long> genShelvesNos(Integer level, Integer count) {
        Long curMax = getMaxByLayer(level);
        List<ErpShelfNoEntity> addList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            curMax++;

            ErpShelfNoEntity entity = new ErpShelfNoEntity();
            entity.setLayer(level);
            entity.setNo(curMax);
            entity.setDisplayNo(String.valueOf(curMax));
            addList.add(entity);
        }

        erpShelvesNoDao.insertBatchSomeColumn(addList);
        return addList.stream().map(ErpShelfNoEntity::getNo).sorted().collect(Collectors.toList());
    }

    private Long getMaxByLayer(Integer level) {
        QueryWrapper<ErpShelfNoEntity> wrapper = new QueryWrapper<>();
        wrapper.select("max(no) no")
                .eq("layer", level);
        ErpShelfNoEntity maxEntity = erpShelvesNoDao.selectOne(wrapper);

        Long curMax = 1000L;
        if (maxEntity != null) {
            curMax = maxEntity.getNo();
        }

        return curMax;
    }
}
