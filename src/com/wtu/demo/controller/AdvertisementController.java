package com.wtu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping
public class AdvertisementController extends BaseController {

	@RequestMapping("/advertisement/index")
	public String index(){
        return "redirect:/html/advertisement/index.jsp";
	}

	@RequestMapping("/advertisement/create")
	@ResponseBody
	public String createAdvertisement(
	        @RequestParam("weight") String weight,
	        @RequestParam("detail") String detail) {
	    System.out.println(weight);
	    System.out.println(detail.toString());
	    return JsonUtil.convertObjectToJson("true");
	}
}
