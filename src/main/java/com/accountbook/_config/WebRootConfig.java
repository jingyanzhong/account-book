package com.accountbook._config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * [Web Root 設定檔]
 *
 * @author cano.su
 * @since 2021/05/31
 */
@ComponentScan("_config")
@PropertySource(value = { "classpath:sys.properties" }, ignoreResourceNotFound = true, encoding = "UTF-8")
public class WebRootConfig {

    @Value("${dataSource.jndiName}")
    public String jndiName;

    public WebRootConfig() {
        super();
        SpringMvcInitializer.logInfo("初始化系統");
    }

    /**
     * [DateSource 使用 JNDI]
     *
     * @author cano.su
     * @since 2021/06/01
     */
    @Primary
    @Bean
    public DataSource dataSource() {
        SpringMvcInitializer.logInfo("建立 DateSource");
        JndiDataSourceLookup lookup = new JndiDataSourceLookup();
        return lookup.getDataSource(jndiName);
    }

    /**
     * [JdbcTemplate 注入 DataSource]
     *
     * @author cano.su
     * @since 2021/06/01
     */
    @Primary
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        SpringMvcInitializer.logInfo("建立 JdbcTemplate");
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver createMultipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(10000000);
        return resolver;
    }

}
