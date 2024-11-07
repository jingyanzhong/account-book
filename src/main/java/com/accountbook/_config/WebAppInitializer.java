package com.accountbook._config;

import javax.servlet.ServletContext;

import org.springframework.web.WebApplicationInitializer;

/**
 * [Web 應用初始化程序]
 * 
 * @author cano.su
 * @since 2022/06/10
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
//        servletContext.addListener(new SessionListener());
    }
}
