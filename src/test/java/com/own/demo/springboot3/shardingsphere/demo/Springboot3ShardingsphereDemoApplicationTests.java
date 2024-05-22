package com.own.demo.springboot3.shardingsphere.demo;

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
        var list = Stream.iterate(1, n -> n + 1).limit(100)
                .map(index -> {
                    var entity = new TestMybatis();
                    entity.setName("name_" + index);
                    entity.setAge(index + 1);
                    return entity;
                })
                .toList();
        testMybatisMapper.insertBatch(list);
    }

}
