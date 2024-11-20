package com.accountbook.model.vo;

import com.accountbook.model.dto.Subject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * [JSON]
 * 
 * @author cano.su
 * @since 2024/11/15
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonSubject {

    /** 會計科目代號 */
    private String code;

    /** 會計科目名稱 */
    private String name;

    public void setCode(String code) {
        this.code = code;
    }

    public JsonSubject() {
        super();
    }

    private JsonSubject(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    public static JsonSubject of(Subject subject) {
        return new JsonSubject(subject.getCode(), subject.getName());
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
