package com.accountbook._config;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * [Spring MVC 初始化]
 *
 * @author cano.su
 * @since 2022/04/27
 */
public class SpringMvcInitializer {

    private static final Logger logger = LogManager.getLogger(WebRootConfig.class);

    public static AtomicInteger systemBuildSerial = new AtomicInteger(1);

    public static void logInfo(String info) {
        logger.info(" {}.{}", systemBuildSerial.getAndIncrement(), info);
    }

}
