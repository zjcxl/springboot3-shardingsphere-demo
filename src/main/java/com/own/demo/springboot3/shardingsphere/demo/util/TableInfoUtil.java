package com.own.demo.springboot3.shardingsphere.demo.util;

import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TableInfoUtil
 *
 * @author chenxueli
 * @date 2021/5/15 10:49
 */
public class TableInfoUtil {

    private final TableInfo tableInfo;

    public TableInfoUtil(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public String getAllInsertSqlProperty(final String prefix) {
        var newPrefix = prefix == null ? "" : prefix;
        return tableInfo.getKeyInsertSqlProperty(true, newPrefix, true)
                + tableInfo.getFieldList().stream().map((i) -> i.getInsertSqlProperty(newPrefix)).filter(Objects::nonNull).collect(Collectors.joining("\n"));
    }

    public String getAllInsertSqlColumn() {
        return tableInfo.getKeyInsertSqlColumn(true, null, true)
                + tableInfo.getFieldList().stream().map(TableFieldInfo::getInsertSqlColumn).filter(Objects::nonNull).collect(Collectors.joining("\n"));
    }

}
