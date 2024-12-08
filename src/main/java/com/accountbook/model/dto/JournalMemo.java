package com.accountbook.model.dto;

import java.util.Objects;

/**
 * [DTO]
 * 
 * @author cano.su
 * @since 2024/12/08
 */
public class JournalMemo {

    /** 摘要代號 */
    private final String code;

    /** 摘要名稱 */
    private final String name;

    private JournalMemo(String code, String name) {
        super();
        this.code = Objects.requireNonNull(code, "摘要代號 must not be null.");
        this.name = Objects.requireNonNull(name, "摘要名稱 must not be null.");
    }

    /** 讀取紀錄 */
    public static JournalMemo ofRead(String code, String name) {
        return new JournalMemo(code, name);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}