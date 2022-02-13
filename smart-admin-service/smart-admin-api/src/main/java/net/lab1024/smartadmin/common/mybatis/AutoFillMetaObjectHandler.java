package net.lab1024.smartadmin.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import net.lab1024.smartadmin.util.SmartRequestTokenUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AutoFillMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createUser", SmartRequestTokenUtil.getLoginName(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateUser", SmartRequestTokenUtil.getLoginName(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateUser", SmartRequestTokenUtil.getLoginName(), metaObject);
    }
}
