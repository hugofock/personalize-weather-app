package com.pwa.vo;

import com.pwa.common.vo.BasicResponseEntityVO;
import org.junit.Test;
import org.omg.CORBA.Object;

import static org.junit.Assert.*;

public class BasicResponseEntityVOTest {

    @Test
    public void buildWithAllInformation() {

        BasicResponseEntityVO<Object> basicResponseEntityVO = new BasicResponseEntityVO<>();
        basicResponseEntityVO.setMessage("message");
        basicResponseEntityVO.setObject(null);
        basicResponseEntityVO.setStatus("city");
        basicResponseEntityVO.setUrl("url");

        assertEquals("message", basicResponseEntityVO.getMessage());
        assertNull(basicResponseEntityVO.getObject());
        assertEquals("city", basicResponseEntityVO.getStatus());
        assertEquals("url", basicResponseEntityVO.getUrl());
        assertNotNull(basicResponseEntityVO.toString());

    }

}