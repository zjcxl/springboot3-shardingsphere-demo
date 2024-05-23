package com.own.demo.springboot3.shardingsphere.demo.config;

import com.github.pagehelper.PageInterceptor;
import jakarta.annotation.Resource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 自定注入分页插件
 *
 * @author liuzh
 */
@SpringBootConfiguration
@EnableConfigurationProperties({PageHelperStandardProperties.class})
@Lazy(false)
public class PageHelperAutoConfiguration implements InitializingBean {

    @Resource
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Resource
    private PageHelperStandardProperties properties;

    @Override
    public void afterPropertiesSet() {
        // 初始化分页拦截器
        var interceptor = new PageInterceptor();
        // 设置参数
        interceptor.setProperties(this.properties.getProperties());
        for (var factory : sqlSessionFactoryList) {
            var configuration = factory.getConfiguration();
            // 判断是否已经存在相同的拦截器
            if (!containsInterceptor(configuration, interceptor)) {
                configuration.addInterceptor(interceptor);
            }
        }
    }

    /**
     * 是否已经存在相同的拦截器
     *
     * @param configuration 配置信息
     * @param interceptor   拦截器
     * @return 是否存在相同的拦截器
     */
    private boolean containsInterceptor(org.apache.ibatis.session.Configuration configuration, Interceptor interceptor) {
        try {
            return configuration.getInterceptors().stream().anyMatch(config -> interceptor.getClass().isAssignableFrom(config.getClass()));
        } catch (Exception e) {
            return false;
        }
    }

}
