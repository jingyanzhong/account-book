package com.accountbook._config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * [Web JPA 設定檔]
 *
 * <pre>
 * * &#64;EnableJpaRepositories      : 啟用 JPA Repository 及設定
 * * &#64;EnableTransactionManagement: 啟用事務管理, 配合 @Transactional 使用
 * </pre>
 * 
 * @author cano.su
 * @since 2022/09/05
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.accountbook.model.dao" }) // 掃描 JpaRepository
@EnableTransactionManagement
public class WebJpaConfig {

    private final String DEFAULT_ENTITY_MANAGER = "defaultEntityManager";

    /**
     * [Entity 管理工廠物件]
     * 
     * <pre>
     * * 設置對應 Hibernate 的 Session Factory 用
     * </pre>
     * 
     * @author cano.su
     * @since 2022/09/06
     */
    @PersistenceContext(unitName = DEFAULT_ENTITY_MANAGER)
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) throws NamingException {
        SpringMvcInitializer.logInfo("Entity 管理工廠物件");

        // 指定設置 Hibernate 作為 JPA Provider
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        Properties properties = new Properties();
        // properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
        // properties.setProperty("hibernate.show_sql", "true");

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(new String[] { "com.canox.adms.model.po" }); // scan entity
        factory.setDataSource(dataSource);
        factory.setJpaProperties(properties);
        factory.setPersistenceUnitName(DEFAULT_ENTITY_MANAGER);
        return factory;
    }

    /** 建立 事務管理器 */
    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        SpringMvcInitializer.logInfo("建立 JPA 事務管理器");

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(factory);
        return txManager;
    }

    /**
     * [設置 異常轉換處理器]
     * 
     * <pre>
     * * 通過使用 &#64;Repository, 它將啟用自動持久化
     * * 當 &#64;Repository 的物件在拋出本地異常時, 會自動將其轉換為 Spring 中的 DataAccessException 的子物件異常
     * </pre>
     * 
     * @author cano.su
     * @since 2022/09/05
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor() {
        SpringMvcInitializer.logInfo("設置 JPA 異常轉換處理器");
        return new PersistenceExceptionTranslationPostProcessor();
    }

}