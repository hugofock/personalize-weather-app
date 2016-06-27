package com.pwa.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    private static final String JDBC_DRIVER_KEY = "javax.persistence.jdbc.driver";
    private static final String JDBC_PASS_KEY = "javax.persistence.jdbc.password";
    private static final String JDBC_USER_KEY = "javax.persistence.jdbc.user";
    private static final String JDBC_URL_KEY = "javax.persistence.jdbc.url";

    @Bean
    public EntityManagerFactory createEntitiyManagerFactory() {
        Map<String, String> properties = new HashMap<>();

        //        properties.put(JDBC_DRIVER_KEY, "org.h2.Driver");
        //        properties.put(JDBC_URL_KEY, "jdbc:h2:mem:test");
        //        properties.put(JDBC_USER_KEY, "sa");
        //        properties.put(JDBC_PASS_KEY, "sa");

        properties.put(JDBC_DRIVER_KEY, "com.mysql.jdbc.Driver");
        properties.put(JDBC_URL_KEY, "jdbc:mysql://localhost:3306/flyt");
        properties.put(JDBC_USER_KEY, "root");
        properties.put(JDBC_PASS_KEY, "password");

        return Persistence.createEntityManagerFactory("WPA", properties);
    }

    @Bean
    public EntityManager createEntitiyManager() {
        return createEntitiyManagerFactory().createEntityManager();
    }

}
