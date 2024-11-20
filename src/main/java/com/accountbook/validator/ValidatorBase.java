package com.accountbook.validator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * [驗證器] Base
 * 
 * @author cano.su
 * @since 2024/11/20
 */
public abstract class ValidatorBase {

    @SuppressWarnings("unchecked")
    protected static List<ValidatorBase> getValidators(Class<? extends ValidatorBase>... validators) {
        return Stream.of(validators)
                .map(validator -> {
                    try {
                        return validator.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new IllegalArgumentException("建立 " + validator.getSimpleName() + " 驗證器失敗, 未有空參數建構子");
                    }
                })
                .collect(Collectors.toList());
    }
}
