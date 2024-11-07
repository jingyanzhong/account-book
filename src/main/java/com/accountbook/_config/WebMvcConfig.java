package com.accountbook._config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.accountbook.utils.SpringUtils;

/**
 * [Web Servlet(MVC) 設定檔]
 *
 * @author cano.su
 * @since 2022/04/27
 */
@EnableWebMvc
@ComponentScan({
        "com.accountbook.*"
})
public class WebMvcConfig implements WebMvcConfigurer {

    public static final String ENCODING_UTF8 = "UTF-8";

    @Autowired
    public ApplicationContext applicationContext;

    public WebMvcConfig() {
        super();
        SpringMvcInitializer.logInfo("初始化 Spring MVC");
    }

    @PostConstruct
    public void registerBeansToFollow() {
        // 註冊要關注的 Spring 容器中的物件
        SpringUtils.registerBeansToFollow(applicationContext);
    }

    /**
     * [配置 默認的首頁]
     * 
     * @author cano.su
     * @since 2024/11/07
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 配置默認的首頁
        // registry.addViewController("/").setViewName("forward:/account-book/index.html");
    }

    /**
     * [開啟 靜態資源]
     * 
     * @author cano.su
     * @since 2024/11/07
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * [設定 靜態資源]
     * 
     * @author cano.su
     * @since 2024/11/07
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
