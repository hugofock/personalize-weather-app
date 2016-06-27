package com.pwa.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pwa.common.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public abstract class BaseVO implements Serializable {

    protected static final long serialVersionUID = 1L;

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseVO.class);

    @Override
    @JsonIgnore
    public String toString() {
        return ObjectUtil.toJson(this);
    }

}
