package com.accountbook.repository.procedure;

import java.util.Objects;

/**
 * [TODO]
 * 
 * @author cano.su
 * @since 2024/12/10
 */
public class ProcedureParam {

    private Integer no;
    private Class<?> type;
    private Object value;

    private ProcedureParam(Integer no, Class<?> type, Object value) {
        super();
        this.no = Objects.requireNonNull(no, "Procedure 參數編號");
        this.type = Objects.requireNonNull(type, "type 參數編號");
        this.value = Objects.requireNonNull(value, "value 參數編號");
    }

    public static ProcedureParam of(Integer no, Class<?> type, Object value) {
        return new ProcedureParam(no, type, value);
    }

    public Integer getNo() {
        return no;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

}
