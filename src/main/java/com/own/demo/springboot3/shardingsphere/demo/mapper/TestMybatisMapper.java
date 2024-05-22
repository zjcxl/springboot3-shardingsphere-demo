package com.own.demo.springboot3.shardingsphere.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.own.demo.springboot3.shardingsphere.demo.entity.TestMybatis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TestMybatisMapper
 *
 * @author chenxueli
 * @date 2024-05-22 10:13:53
 */
@Mapper
public interface TestMybatisMapper extends BaseMapper<TestMybatis> {

    /**
     * 批量插入
     *
     * @param list 批量插入的列表信息
     * @return 影响行数
     */
    int insertBatch(@Param("list") List<TestMybatis> list);

}
