package com.wtu.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wtu.demo.model.Advertisement;
import com.wtu.demo.service.impl.AdvertisementServiceImpl;
import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping(value="/advertisement")
public class AdvertisementController extends BaseController {

	@Autowired
	private AdvertisementServiceImpl advertisementServiceImpl;

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
	    ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/html/advertisement/index.jsp");

		return modelAndView;
	}

	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/html/advertisement/create.jsp");

		return modelAndView;
	}

	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView editPage(@RequestParam("advertisementId") String advertisementId){
        ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/html/advertisement/create.jsp");
		modelAndView.addObject("advertisementId", advertisementId);

		return modelAndView;
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

	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public String editAdvertisement(@RequestParam("id") String id,
	        @RequestParam("categoryId") String categoryId,
	        @RequestParam("weight") String weight,
	        @RequestParam("description") String description,
	        @RequestParam("detail") String detail) {
		boolean result = false;

		result = advertisementServiceImpl.editAdvertisement(id, categoryId, weight, description, detail);

		return JsonUtil.convertObjectToJson(result);
	}

	@RequestMapping(value="/getAdvertisementDetailPage", method=RequestMethod.GET)
    public ModelAndView getAdvertisementDetailPage(@RequestParam("advertisementId") String advertisementId) {
	    ModelAndView modelAndView = new ModelAndView();

	    modelAndView.setViewName("/html/advertisement/templet.jsp");
	    modelAndView.addObject("advertisementId", advertisementId);

        return modelAndView;
    }

	@RequestMapping(value="/getAdvertisementById", method=RequestMethod.GET)
	@ResponseBody
    public String getAdvertisementById(@RequestParam("advertisementId") String advertisementId) {
        Advertisement advertisement = advertisementServiceImpl.getAdvertisementById(advertisementId);

        return JsonUtil.convertObjectToJson(advertisement);
    }

	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	@ResponseBody
	public String getAllAdvertisement(@RequestParam("index") String index,
			@RequestParam("pageSize") String pageSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Advertisement> advertisements = advertisementServiceImpl.getAllAdvertisement(index, pageSize);
		Long count = advertisementServiceImpl.getAdvertisementCount();
		Long pageCount = count%Long.valueOf(pageSize) == 0 ? count/Long.valueOf(pageSize) : count/Long.valueOf(pageSize) + 1;

		result.put("advertisements", advertisements);
		result.put("index", index);
		result.put("pageCount", pageCount);

		return JsonUtil.convertObjectToJson(result);
	}

	@RequestMapping(value="/getAllByCategoryId", method=RequestMethod.GET)
	@ResponseBody
	public String getAllAdvertisementByCategoryId(@RequestParam("categoryId") String categoryId,
			@RequestParam("index") String index,
			@RequestParam("pageSize") String pageSize) {
		List<Advertisement> advertisements = advertisementServiceImpl.getAllAdvertisementByCategoryId(categoryId, index, pageSize);

		return JsonUtil.convertObjectToJson(advertisements);
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
    @ResponseBody
    public String deleteAdvertisementById(@RequestParam("id") String id) {
	    boolean result = advertisementServiceImpl.deleteAdvertisementById(id);

        return JsonUtil.convertObjectToJson(result);
    }
}
