package com.own.demo.springboot3.shardingsphere.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Optional;
import java.util.Properties;

/**
 * PageHelperStandardProperties
 *
 * @author chenxueli
 * @date 2024-05-23 09:33:47
 */
@Data
@ConfigurationProperties(prefix = PageHelperStandardProperties.PAGEHELPER_PREFIX)
public class PageHelperStandardProperties {

    public static final String PAGEHELPER_PREFIX = "pagehelper";

    private static Properties DEFAULT_PROPERTIES = null;

    private Boolean offsetAsPageNum;
    private Boolean rowBoundsWithCount;
    private Boolean pageSizeZero;
    private Boolean reasonable;
    private Boolean supportMethodsArguments;
    private String dialect;
    private String helperDialect;
    private Boolean autoRuntimeDialect;
    private Boolean autoDialect;
    private Boolean closeConn;
    private String params;
    private Boolean defaultCount;
    private String dialectAlias;
    private String autoDialectClass;
    private Boolean useSqlserver2012;
    private String countColumn;
    private String replaceSql;
    private String sqlCacheClass;
    private String boundSqlInterceptors;
    private Boolean keepOrderBy;
    private Boolean keepSubSelectOrderBy;
    private String sqlParser;
    private Boolean asyncCount;
    private String countSqlParser;
    private String orderBySqlParser;
    private String sqlServerSqlParser;

    public Properties getProperties() {
        if (DEFAULT_PROPERTIES == null) {
            var properties = new Properties();
            Optional.ofNullable(offsetAsPageNum).ifPresent(value -> properties.setProperty("offsetAsPageNum", value.toString()));
            Optional.ofNullable(rowBoundsWithCount).ifPresent(value -> properties.setProperty("rowBoundsWithCount", value.toString()));
            Optional.ofNullable(pageSizeZero).ifPresent(value -> properties.setProperty("pageSizeZero", value.toString()));
            Optional.ofNullable(reasonable).ifPresent(value -> properties.setProperty("reasonable", value.toString()));
            Optional.ofNullable(supportMethodsArguments).ifPresent(value -> properties.setProperty("supportMethodsArguments", value.toString()));
            Optional.ofNullable(dialect).ifPresent(value -> properties.setProperty("dialect", value));
            Optional.ofNullable(helperDialect).ifPresent(value -> properties.setProperty("helperDialect", value));
            Optional.ofNullable(autoRuntimeDialect).ifPresent(value -> properties.setProperty("autoRuntimeDialect", value.toString()));
            Optional.ofNullable(autoDialect).ifPresent(value -> properties.setProperty("autoDialect", value.toString()));
            Optional.ofNullable(closeConn).ifPresent(value -> properties.setProperty("closeConn", value.toString()));
            Optional.ofNullable(params).ifPresent(value -> properties.setProperty("params", value));
            Optional.ofNullable(defaultCount).ifPresent(value -> properties.setProperty("defaultCount", value.toString()));
            Optional.ofNullable(dialectAlias).ifPresent(value -> properties.setProperty("dialectAlias", value));
            Optional.ofNullable(autoDialectClass).ifPresent(value -> properties.setProperty("autoDialectClass", value));
            Optional.ofNullable(useSqlserver2012).ifPresent(value -> properties.setProperty("useSqlserver2012", value.toString()));
            Optional.ofNullable(countColumn).ifPresent(value -> properties.setProperty("countColumn", value));
            Optional.ofNullable(replaceSql).ifPresent(value -> properties.setProperty("replaceSql", value));
            Optional.ofNullable(sqlCacheClass).ifPresent(value -> properties.setProperty("sqlCacheClass", value));
            Optional.ofNullable(boundSqlInterceptors).ifPresent(value -> properties.setProperty("boundSqlInterceptors", value));
            Optional.ofNullable(keepOrderBy).ifPresent(value -> properties.setProperty("keepOrderBy", value.toString()));
            Optional.ofNullable(keepSubSelectOrderBy).ifPresent(value -> properties.setProperty("keepSubSelectOrderBy", value.toString()));
            Optional.ofNullable(sqlParser).ifPresent(value -> properties.setProperty("sqlParser", value));
            Optional.ofNullable(asyncCount).ifPresent(value -> properties.setProperty("asyncCount", value.toString()));
            Optional.ofNullable(countSqlParser).ifPresent(value -> properties.setProperty("countSqlParser", value));
            Optional.ofNullable(orderBySqlParser).ifPresent(value -> properties.setProperty("orderBySqlParser", value));
            Optional.ofNullable(sqlServerSqlParser).ifPresent(value -> properties.setProperty("sqlServerSqlParser", value));
            DEFAULT_PROPERTIES = properties;
        }
        return DEFAULT_PROPERTIES;
    }

}
