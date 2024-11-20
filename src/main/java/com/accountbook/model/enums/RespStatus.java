package com.accountbook.model.enums;

/**
 * [回應狀態]
 * 
 * @author cano.su
 * @since 2024/11/16
 */
public enum RespStatus {

    /** [200]成功 */
    _200("成功"),

    /** [A01]新增異常 */
    _A01("新增異常"),

    /** [Q01]查無資料 */
    _Q01("查無資料"),

    /** [M01]更新異常 */
    _M01("更新異常"),

    /** [R01]移除異常 */
    _R01("移除異常"),

    /** [X01]系統出現不明異常 */
    _X01("系統出現不明異常");

    private final String PREFIX = "_";

    /** 訊息 */
    private String message;

    private RespStatus(String message) {
        this.message = message;
    }

    /** 取得 回應碼 */
    public String getCode() {
        return this.name().replace(PREFIX, "");
    }

    public String getMessage() {
        return message;
    }

}
