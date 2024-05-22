package com.own.demo.springboot3.shardingsphere.demo.em;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * SqlMethod
 *
 * @author chenxueli
 * @date 2024-05-22 13:45:01
 */
@Getter
@AllArgsConstructor
public enum SqlMethod {
    /**
     * 批量插入（全字段）
     */
    INSERT_BATCH("insertBatch", "批量插入（全字段）", "<script> insert into %s %s value %s</script>"),
    ;

    private final String method;
    private final String desc;
    private final String sql;

}
