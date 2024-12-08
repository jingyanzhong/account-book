package com.accountbook.model.vo;

import com.accountbook.model.dto.JournalMemo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * [JSON]
 * 
 * @author cano.su
 * @since 2024/12/08
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonJournalMemo implements JsonVo<JournalMemo> {

    /** 摘要代號 */
    private String code;

    /** 摘要名稱 */
    private String name;

    private JsonJournalMemo() {
        super();
    }

    public static JsonJournalMemo of(JournalMemo dto) {
        return new JsonJournalMemo()
                .setCode(dto.getCode())
                .setName(dto.getName());
    }

    public JsonJournalMemo setCode(String code) {
        this.code = code;
        return this;
    }

    public JsonJournalMemo setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
