package com.accountbook.exception.enums;

/**
 * [EXCEPTION TYPE]
 * 
 * @author cano.su
 * @since 2024/11/16
 */
public enum JournalRecordExceptionType {

    ADD_VALID_ERROR("新增時出現異常"),
    ADD_VALID_ERROR_PARAM("新增時驗證參數錯誤"),
    QUERY_NOT_FOUND("查無紀錄"),
    QUERY_VALID_ERROR("查詢時出現異常"),
    UPDATE_VALID_ERROR("更新時出現異常"),
    UPDATE_VALID_ERROR_PARAM("更新時驗證參數錯誤"),
    REMOVE_VALID_ERROR("移除時出現異常"),
    REMOVE_VALID_ERROR_PARAM("移除時驗證參數錯誤");

    /** 異常訊息 */
    private String message;

    private JournalRecordExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
