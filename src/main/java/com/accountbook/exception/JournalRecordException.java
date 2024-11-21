package com.accountbook.exception;

import com.accountbook.exception.enums.JournalRecordExceptionType;

/**
 * [EXCEPTION]
 * 
 * @author cano.su
 * @since 2024/11/16
 */
public class JournalRecordException extends Exception {

    private static final long serialVersionUID = 7605392130795333163L;

    /** 異常類型 */
    private JournalRecordExceptionType type;

    public JournalRecordException(JournalRecordExceptionType type) {
        super(type.getMessage());
        this.type = type;
    }

    public JournalRecordException(JournalRecordExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public JournalRecordException(JournalRecordExceptionType type, Throwable throwable) {
        super(type.getMessage(), throwable);
        this.type = type;
    }

    public JournalRecordException(JournalRecordExceptionType type, String message, Throwable throwable) {
        super(message, throwable);
        this.type = type;
    }

    /** 新增時出現異常 */
    public static JournalRecordException ofAddValidError(String message) {
        return new JournalRecordException(JournalRecordExceptionType.ADD_VALID_ERROR, message);
    }

    /** 新增時驗證參數錯誤 */
    public static JournalRecordException ofAddValidErrorParam(String message) {
        return new JournalRecordException(JournalRecordExceptionType.ADD_VALID_ERROR_PARAM, message);
    }

    /** 查無資料 */
    public static JournalRecordException ofQueryNotFound() {
        return new JournalRecordException(JournalRecordExceptionType.QUERY_NOT_FOUND);
    }

    /** 查詢時出現異常 */
    public static JournalRecordException ofQueryValidError() {
        return new JournalRecordException(JournalRecordExceptionType.QUERY_VALID_ERROR);
    }

    /** 更新時出現異常 */
    public static JournalRecordException ofUpdateValidError(String message) {
        return new JournalRecordException(JournalRecordExceptionType.UPDATE_VALID_ERROR, message);
    }

    /** 更新時驗證參數錯誤 */
    public static JournalRecordException ofUpdateValidErrorParam(String message) {
        return new JournalRecordException(JournalRecordExceptionType.UPDATE_VALID_ERROR_PARAM, message);
    }

    /** 移除時出現異常 */
    public static JournalRecordException ofRemoveValidError(String message) {
        return new JournalRecordException(JournalRecordExceptionType.REMOVE_VALID_ERROR, message);
    }

    /** 移除時驗證參數錯誤 */
    public static JournalRecordException ofRemoveValidErrorParam(String message) {
        return new JournalRecordException(JournalRecordExceptionType.REMOVE_VALID_ERROR_PARAM, message);
    }

    public JournalRecordExceptionType getType() {
        return type;
    }

}
