package com.accountbook.controller.base;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * [API Controller]
 *
 * @author cano.su
 * @since 2022/07/31
 */
@CrossOrigin
public abstract class ApiController extends BaseController {

    @Override
    public int countPages() {
        return 0;
    }

    /** 計算 API 數量 */
    public int countAPIs() {
        final Class<?> apiControllerClass = this.getClass();
        final Field[] fields = apiControllerClass.getDeclaredFields();
        final Long total = Arrays.stream(fields)
                .filter(f -> f.getName().contains("API"))
                .count();
        return total.intValue();
    }
}
