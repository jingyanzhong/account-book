package com.accountbook._config;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * [Spring MVC 初始化]
 *
 * @author cano.su
 * @since 2022/04/27
 */
public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final Logger logger = LogManager.getLogger(SpringMvcInitializer.class);

    public static AtomicInteger systemBuildSerial = new AtomicInteger(1);

    public static void logInfo(String info) {
        logger.info(" {}.{}", systemBuildSerial.getAndIncrement(), info);
    }

    public SpringMvcInitializer() {
        super();
        SpringMvcInitializer.logInfo("系統初始化開始");
    }

    /**
     * [設定檔註冊] -DataSource 配置
     *
     * @author cano.su
     * @since 2022/04/27
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { WebRootConfig.class };
    }

    /**
     * [Servlet 註冊 (MVC)]
     *
     * @author cano.su
     * @since 2022/04/27
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebMvcConfig.class };
    }

    /**
     * [設定 Servlet(Spring) 管理路徑]
     *
     * @author cano.su
     * @since 2022/04/27
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/api/*" }; // 全部
    }

}
