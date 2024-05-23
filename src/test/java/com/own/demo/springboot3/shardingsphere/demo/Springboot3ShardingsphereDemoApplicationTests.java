package com.own.demo.springboot3.shardingsphere.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.own.demo.springboot3.shardingsphere.demo.entity.TestMybatis;
import com.own.demo.springboot3.shardingsphere.demo.mapper.TestMybatisMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest(classes = Springboot3ShardingsphereDemoApplication.class)
class Springboot3ShardingsphereDemoApplicationTests {

    @Resource
    private TestMybatisMapper testMybatisMapper;

    @Test
    void add() {
        for (int i = 0; i < 10; i++) {
            var entity = new TestMybatis();
            entity.setName("name_" + i);
            entity.setAge(i + 1);
            testMybatisMapper.insert(entity);
        }
    }

    @Test
    void batchAdd() {
        var list = Stream.iterate(1, n -> n + 1).limit(100).map(index -> {
            var entity = new TestMybatis();
            entity.setName("name_" + index);
            entity.setAge(index + 1);
            return entity;
        }).toList();
        testMybatisMapper.insertBatch(list);
    }

    @Test
    void select() {
        var row = 12;
        var page = PageHelper.startPage(1, row);
        var list = testMybatisMapper.selectList(new LambdaQueryWrapper<>());
        var pageInfo = PageInfo.of(list);
        if (pageInfo.getTotal() > row) {
            assert list.size() == row;
        } else {
            assert list.size() == pageInfo.getTotal();
        }
        page.close();
    }

}
