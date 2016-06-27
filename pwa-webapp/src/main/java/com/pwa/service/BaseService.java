package com.pwa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseService {
    Logger LOGGER = LoggerFactory.getLogger(BaseService.class);
}
