package com.wtu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtu.demo.service.impl.AdvertisementServiceImpl;
import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping(value="/advertisement")
public class AdvertisementController extends BaseController {

	@Autowired
	private AdvertisementServiceImpl advertisementServiceImpl;
	
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
		boolean result = false; 
		
		result = advertisementServiceImpl.createAdvertisement(categoryId, weight, description, detail);
	    
		return JsonUtil.convertObjectToJson(result);
	}
}
