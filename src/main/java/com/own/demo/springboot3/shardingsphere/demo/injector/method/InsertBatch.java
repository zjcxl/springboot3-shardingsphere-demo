package com.own.demo.springboot3.shardingsphere.demo.injector.method;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.own.demo.springboot3.shardingsphere.demo.em.SqlMethod;
import com.own.demo.springboot3.shardingsphere.demo.util.TableInfoUtil;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * InsertBatch
 *
 * @author chenxueli
 * @date 2024-05-22 13:44:30
 */
public class InsertBatch extends AbstractMethod {

    public InsertBatch() {
        super("insertBatch");
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KeyGenerator keyGenerator = new NoKeyGenerator();
        var method = SqlMethod.INSERT_BATCH;
        String keyProperty = null;
        String keyColumn = null;
        var tableInfoUtil = new TableInfoUtil(tableInfo);
        var columnScript = SqlScriptUtils.convertTrim(tableInfoUtil.getAllInsertSqlColumn(), "(", ")", null, ",");
        var valuesScript = SqlScriptUtils.convertForeach(
                SqlScriptUtils.convertTrim(tableInfoUtil.getAllInsertSqlProperty("item."), "(", ")", null, ","),
                "list", null, "item", ",");
        if (StringUtils.isNotBlank(tableInfo.getKeyProperty())) {
            if (tableInfo.getIdType() == IdType.AUTO) {
                keyGenerator = new Jdbc3KeyGenerator();
                keyProperty = tableInfo.getKeyProperty();
                keyColumn = tableInfo.getKeyColumn();
            } else if (null != tableInfo.getKeySequence()) {
                keyGenerator = TableInfoHelper.genKeyGenerator("insert", tableInfo, this.builderAssistant);
                keyProperty = tableInfo.getKeyProperty();
                keyColumn = tableInfo.getKeyColumn();
            }
        }
        var sql = String.format(method.getSql(), tableInfo.getTableName(), columnScript, valuesScript);
        var sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, method.getMethod(), sqlSource, keyGenerator, keyProperty, keyColumn);
    }

}
