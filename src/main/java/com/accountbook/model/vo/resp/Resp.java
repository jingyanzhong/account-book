package com.accountbook.model.vo.resp;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.accountbook.model.enums.RespStatus;
import com.accountbook.utils.JsonUtils;

/**
 * [RESP]
 * 
 * @author cano.su
 * @since 2024/11/16
 */
public class Resp {

    private static final Logger logger = LogManager.getLogger(Resp.class);

    /** 回應碼 */
    private String status;

    /** 訊息 */
    private String message;

    /** 資料 */
    private Object data;

    private Resp(RespStatus status, Object data) {
        super();
        this.status = Objects.requireNonNull(status, "回應碼").getCode();
        this.message = status.getMessage();
        this.data = data;
    }

    /** [200]成功 */
    public static String ofSuccess() {
        return new Resp(RespStatus._200, null).toJson();
    }

    /** [200]成功(有帶資料) */
    public static String ofSuccess(Object data) {
        return new Resp(RespStatus._200, data).toJson();
    }

    /** [A01]新增異常 */
    public static String ofAddException(Throwable throwable) {
        logger.error(throwable.getMessage(), throwable);
        return new Resp(RespStatus._A01, throwable.getMessage()).toJson();
    }

    /** [Q01]查無資料 */
    public static String ofQueryNotFound() {
        return new Resp(RespStatus._Q01, null).toJson();
    }

    /** [Q02]查詢異常 */
    public static String ofQueryException(Throwable throwable) {
        logger.error(throwable.getMessage(), throwable);
        return new Resp(RespStatus._Q02, throwable.getMessage()).toJson();
    }

    /** [A02]更新異常 */
    public static String ofModifyException(Throwable throwable) {
        logger.error(throwable.getMessage(), throwable);
        return new Resp(RespStatus._M01, throwable.getMessage()).toJson();
    }

    /** [R02]移除異常 */
    public static String ofRemoveException(Throwable throwable) {
        logger.error(throwable.getMessage(), throwable);
        return new Resp(RespStatus._R01, throwable.getMessage()).toJson();
    }

    /** [X01]系統出現不明異常 */
    public static String ofException(Throwable throwable) {
        logger.error(throwable.getMessage(), throwable);
        return new Resp(RespStatus._X01, throwable.getMessage()).toJson();
    }

    /** 轉成 JSON */
    public String toJson() {
        return JsonUtils.toJson(this);
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

}
