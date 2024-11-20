package com.accountbook.validator;

/**
 * [驗證器]
 * 
 * @author cano.su
 * @since 2024/11/20
 */
public interface ValidatorConsumer<LIB, DATA> {

    /**
     * [驗證]
     * 
     * @author cano.su
     * @since 2024/11/20
     * @param lib  驗證所需資源
     * @param data 待驗證資料
     * @throws ValidatorException 驗證錯誤拋出的異常
     */
    void valid(LIB lib, DATA data) throws ValidatorException;

    /**
     * [取得 驗證器]
     * 
     * @author cano.su
     * @since 2024/11/20
     * @return 驗證器
     */
    ValidatorConsumer<LIB, DATA> getInstance();
}
