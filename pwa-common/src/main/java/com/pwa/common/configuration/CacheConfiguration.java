package com.pwa.common.configuration;

import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration {

    public static final String CACHE_EXPIRATION_MINUTE = "cache_expiration_minute";
    public static final String CACHE_SIZE = "cache_size";
    public static final String CONFIG_PROPERTIES = "config.properties";
    public static final Integer DEFAULT_CACHE_EXPIRATION_MINUTE = 30;
    public static final Integer DEFAULT_CACHE_SIZE = 100;

    protected static final Logger LOGGER = LoggerFactory.getLogger(CacheConfiguration.class);

    @Bean(name = "cacheManager")
    public CacheManager cacheManager() {

        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {

            @Override
            protected Cache createConcurrentMapCache(final String name) {

                Properties prop = new Properties();
                InputStream input = CacheManager.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);

                try {
                    prop.load(input);
                } catch (IOException e) {
                    LOGGER.error("Error during loading config.properties in createConcurrentMapCache method");
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            LOGGER.error("Error during closing config.properties in createConcurrentMapCache method");
                        }
                    }
                }


                Integer cacheExpirationInMinute = DEFAULT_CACHE_EXPIRATION_MINUTE;
                Integer cacheSize = DEFAULT_CACHE_SIZE;

                if (!StringUtils.isEmpty(prop.getProperty(CACHE_EXPIRATION_MINUTE))) {
                    cacheExpirationInMinute = Integer.valueOf(prop.getProperty(CACHE_EXPIRATION_MINUTE));
                }

                if (!StringUtils.isEmpty(prop.getProperty(CACHE_SIZE))) {
                    cacheSize = Integer.valueOf(prop.getProperty(CACHE_SIZE));

                }

                System.out.println("cache created");

                return new ConcurrentMapCache(name, CacheBuilder.newBuilder()
                    .expireAfterWrite(cacheExpirationInMinute, TimeUnit.MINUTES)
                    .maximumSize(cacheSize)
                    .build()
                    .asMap(), false);

            }
        };

        return cacheManager;
    }

}