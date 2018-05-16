package com.wtu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping(value="/advertisement")
public class AdvertisementController extends BaseController {

	@RequestMapping("/index")
	public String index(){
        return "redirect:/html/advertisement/index.jsp";
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public String createAdvertisement(
	        @RequestParam("categoryId") String categoryId,
	        @RequestParam("weight") String weight,
	        @RequestParam("description") String description,
	        @RequestParam("detail") String detail) {
	    System.out.println(weight);
	    System.out.println(detail.toString());
	    return JsonUtil.convertObjectToJson("true");
	}
}
