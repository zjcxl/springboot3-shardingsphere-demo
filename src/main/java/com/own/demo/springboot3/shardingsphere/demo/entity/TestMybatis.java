package com.own.demo.springboot3.shardingsphere.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * TestMybatis
 * <p>
 * 测试mybatis
 *
 * @author chenxueli
 * @date 2024-05-22 10:14:32
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("test_mybatis")
public class TestMybatis {

    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 姓名
     */
    private String name;

}
