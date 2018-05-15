package com.wtu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtu.demo.service.impl.UserServiceImpl;
import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/api/user/signin", method = RequestMethod.GET)
	@ResponseBody
	public String signin(@RequestParam("email") String email, @RequestParam("password") String password) {
        return JsonUtil.convertObjectToJson(userServiceImpl.checkSignIn(email, password));
    }
}
