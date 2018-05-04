package com.wtu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

	@RequestMapping("/reg")
	public String reg() {
		System.out.println("1111");
		return "1111";
	}
}
