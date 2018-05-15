package com.wtu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class Advertisement {

	@RequestMapping("/advertisement/index")
	public String index(){
		return "/html/advertisement/index.jsp";
	}
}
