package net.lab1024.smartadmin.common.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

public interface SmartBaseMapper<T> extends BaseMapper<T> {
    /**
     * 批量插入 仅适用于mysql
     * 注意：调用时必须为非空字段设置值，即使数据库配置了默认值，也需要进行显形赋值
     * todo: 看下是否可以重写对应实现，动态赋值
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);
}
