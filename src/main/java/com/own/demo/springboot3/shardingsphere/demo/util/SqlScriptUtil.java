package com.own.demo.springboot3.shardingsphere.demo.util;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;

/**
 * SqlScriptUtil
 *
 * @author chenxueli
 * @date 2020/9/22 10:17
 */
public class SqlScriptUtil {

    public static String sqlWhereEntityWrapper(boolean newLine, TableInfo table, String where) {
        var sqlScript = "";
        var sortSqlScript = "\n AND " + where;
        if (table.isWithLogicDelete()) {
            sqlScript = table.getAllSqlWhere(false, true, true, "ew.entity.");
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", "ew.entity"), true);
            sqlScript = sqlScript + "\n" + table.getLogicDeleteSql(true, true) + sortSqlScript + "\n";
            var normalSqlScript = SqlScriptUtils.convertIf(String.format("AND ${%s}", "ew.sqlSegment"), String.format("%s != null and %s != '' and %s", "ew.sqlSegment", "ew.sqlSegment", "ew.nonEmptyOfNormal"), true);
            normalSqlScript = normalSqlScript + "\n";
            normalSqlScript = normalSqlScript + SqlScriptUtils.convertIf(String.format(" ${%s}", "ew.sqlSegment"), String.format("%s != null and %s != '' and %s", "ew.sqlSegment", "ew.sqlSegment", "ew.emptyOfNormal"), true);
            sqlScript = sqlScript + normalSqlScript;
            sqlScript = SqlScriptUtils.convertChoose(String.format("%s != null", "ew"), sqlScript, table.getLogicDeleteSql(false, true) + sortSqlScript);
            sqlScript = SqlScriptUtils.convertWhere(sqlScript);
        } else {
            sqlScript = table.getAllSqlWhere(false, false, true, "ew.entity.");
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", "ew.entity"), true);
            sqlScript = sqlScript + "\n";
            sqlScript = sqlScript + SqlScriptUtils.convertIf(String.format(SqlScriptUtils.convertIf(" AND", String.format("%s and %s", "ew.nonEmptyOfEntity", "ew.nonEmptyOfNormal"), false) + " ${%s}", "ew.sqlSegment"), String.format("%s != null and %s != '' and %s", "ew.sqlSegment", "ew.sqlSegment", "ew.nonEmptyOfWhere"), true);
            sqlScript = SqlScriptUtils.convertWhere(sqlScript) + "\n";
            sqlScript = sqlScript + SqlScriptUtils.convertIf(String.format(" ${%s}", "ew.sqlSegment"), String.format("%s != null and %s != '' and %s", "ew.sqlSegment", "ew.sqlSegment", "ew.emptyOfWhere"), true);
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", "ew"), true);
        }
        return newLine ? "\n" + sqlScript : sqlScript;
    }

    public static String convertForeach(final String sqlScript, final String collection, final String index, final String item, final String separator, final String open, final String close) {
        var builder = new StringBuilder("<foreach");
        if (StringUtils.isNotBlank(collection)) {
            builder.append(" collection=\"").append(collection).append("\"");
        }
        if (StringUtils.isNotBlank(index)) {
            builder.append(" index=\"").append(index).append("\"");
        }
        if (StringUtils.isNotBlank(item)) {
            builder.append(" item=\"").append(item).append("\"");
        }
        if (StringUtils.isNotBlank(separator)) {
            builder.append(" separator=\"").append(separator).append("\"");
        }
        if (StringUtils.isNotBlank(open)) {
            builder.append(" open=\"").append(open).append("\"");
        }
        if (StringUtils.isNotBlank(close)) {
            builder.append(" close=\"").append(close).append("\"");
        }
        return builder.append(">").append("\n").append(sqlScript).append("\n").append("</foreach>").toString();
    }

}
