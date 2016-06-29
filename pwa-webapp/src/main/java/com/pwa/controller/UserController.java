package com.pwa.controller;

import com.pwa.common.constant.MimeType;
import com.pwa.service.IUserService;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/users", method = { RequestMethod.GET }, produces = MimeType.JSON)
    public ResponseEntity<Object> users() {
        return null;
    }

}