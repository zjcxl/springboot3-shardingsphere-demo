package com.own.demo.springboot3.shardingsphere.demo.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.own.demo.springboot3.shardingsphere.demo.injector.method.InsertBatch;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * CustomInjector
 *
 * @author chenxueli
 * @date 2024-05-22 13:44:26
 */
@Component
public class CustomInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        var methodList = super.getMethodList(mapperClass, tableInfo);
        methodList.add(new InsertBatch());
        return methodList;
    }

}
