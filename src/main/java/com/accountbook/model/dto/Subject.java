package com.accountbook.model.dto;

import java.util.Objects;

/**
 * [DTO]
 * 
 * @author cano.su
 * @since 2024/11/15
 */
public class Subject {

    /** 會計科目代號 */
    private String code;

    /** 會計科目名稱 */
    private String name;

    private Subject(String code, String name) {
        super();
        this.code = Objects.requireNonNull(code, "會計科目代號 must not be null.");
        this.name = Objects.requireNonNull(name, "會計科目名稱 must not be null.");
    }

    public static Subject of(String code, String name) {
        return new Subject(code, name);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
