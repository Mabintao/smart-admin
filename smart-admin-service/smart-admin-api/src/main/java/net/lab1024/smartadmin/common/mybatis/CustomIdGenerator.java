package net.lab1024.smartadmin.common.mybatis;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import net.lab1024.smartadmin.util.SmartStringUtil;
import org.springframework.stereotype.Component;

@Component
public class CustomIdGenerator extends DefaultIdentifierGenerator {
    @Override
    public String nextUUID(Object entity) {
        return SmartStringUtil.genRandomId();
    }
}
