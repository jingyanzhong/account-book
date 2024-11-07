package com.accountbook.controller.base;

import java.lang.reflect.Field;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

/**
 * [基礎 Controller]
 *
 * @author cano.su
 * @since 2022/06/10
 */
public abstract class BaseController {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    protected final String PREFIX_PAGE = "PAGE";
    protected final String PREFIX_API = "API";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpSession session;

    @Autowired
    protected HttpServletResponse response;

    public <T> T getBean(Class<T> beanClass) {
        return webApplicationContext.getBean(beanClass);
    }

    /**
     * [計算 頁面 數量]
     * 
     * @author cano.su
     * @since 2024/01/27
     * @return
     */
    public abstract int countPages();

    /**
     * [計算 API 數量]
     * 
     * @author cano.su
     * @since 2024/01/27
     * @return
     */
    public int countAPIs() {
        final Class<?> controllerClass = this.getClass();
        final Field[] fields = controllerClass.getDeclaredFields();
        final Long total = Arrays.stream(fields)
                .filter(f -> f.getName().contains(PREFIX_API))
                .count();
        return total.intValue();
    }

}
