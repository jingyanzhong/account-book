package com.accountbook.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang.NullArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

/**
 * [Spring 工具]
 * 
 * @author cano.su
 * @since 2023/04/13
 */
public class SpringUtils {

    private static final Logger logger = LogManager.getLogger(SpringUtils.class);

    /** 要關注的 Spring 容器中的物件 */
    private static Class<?>[] beansToFollow = {
    }; // 多打 "," 不影響編譯, 但考量日後新增時, git 紀錄較為乾淨

    private static Map<String, Object> beans = new HashMap<>();

    /** 註冊要關注的 Spring 容器中的物件 */
    public static void registerBeansToFollow(ApplicationContext applicationContext) {
        if (Objects.isNull(applicationContext)) {
            throw new NullArgumentException("applicationContext");
        }
        for (int i = 0; i < beansToFollow.length; i++) {
            Class<?> bean = beansToFollow[i];
            Optional<Object> springBeanOpt = Optional.ofNullable(applicationContext.getBean(bean));
            if (springBeanOpt.isPresent()) {
                beans.put(bean.getName(), springBeanOpt.get());
            } else {
                logger.error(String.format("取得 %s 為空, 物件未被註冊在 spring context 中!!", bean.getName()));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <Bean> Bean getBean(Class<Bean> beanClass) {
        if (Objects.isNull(beanClass)) {
            throw new NullArgumentException("beanClass");
        }
        Optional<Bean> beanOpt = Optional.ofNullable((Bean) beans.get(beanClass.getName()));
        if (!beanOpt.isPresent()) {
            logger.error(
                    String.format("從 spring context 取得 %s 為空, 請確認是否有手動註冊該物件至 beansToFollow 中!!",
                            beanClass.getName()));
        }
        return beanOpt.orElse(null);
    }
}
