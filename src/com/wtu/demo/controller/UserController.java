package com.wtu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    //@Autowired
    //private UserServiceImpl userService;

	@RequestMapping("/signin")
	public String signin() {
	    System.out.println("111111");
	    return "1111";
    }
}
