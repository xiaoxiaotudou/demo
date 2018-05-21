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

import com.wtu.demo.model.AdvertisementCategory;
import com.wtu.demo.service.impl.AdvertisementCategoryServiceImpl;
import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping("/advertisementCategory")
public class AdvertisementCategoryController extends BaseController {

    @Autowired
    private AdvertisementCategoryServiceImpl advertisementCategoryServiceImpl;

    @RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
	    ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/html/advertisementcategory/index.jsp");

		return modelAndView;
	}

    @RequestMapping(value="/create", method=RequestMethod.POST)
    @ResponseBody
    public String createAdvertisementCategory(@RequestParam("categoryName") String categoryName) {
        boolean result = advertisementCategoryServiceImpl.createAdvertisementCategory(categoryName);

        return JsonUtil.convertObjectToJson(result);
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    @ResponseBody
    public String editAdvertisementCategoryById(@RequestParam("id") String id, @RequestParam("categoryName") String categoryName) {
        boolean result = advertisementCategoryServiceImpl.editAdvertisementCategoryById(id, categoryName);

        return JsonUtil.convertObjectToJson(result);
    }


    @RequestMapping(value="/get", method=RequestMethod.GET)
    @ResponseBody
    public String getAdvertisementCategoryById(@RequestParam("id") String id) {
        AdvertisementCategory advertisementCategory = advertisementCategoryServiceImpl.getAdvertisementCategoryById(id);

        return JsonUtil.convertObjectToJson(advertisementCategory);
    }

    @RequestMapping(value="/getAll", method=RequestMethod.GET)
    @ResponseBody
    public String getAllAdvertisementCategory() {
        List<AdvertisementCategory> advertisementCategories = advertisementCategoryServiceImpl.getAllAdvertisementCategory();

        return JsonUtil.convertObjectToJson(advertisementCategories);
    }


    @RequestMapping(value="/getAllByPagination", method=RequestMethod.GET)
    @ResponseBody
    public String getAllAdvertisementCategoryByPagination(@RequestParam("index") String index,
			@RequestParam("pageSize") String pageSize) {
    	Map<String, Object> result = new HashMap<String, Object>();
        List<AdvertisementCategory> advertisementCategories = advertisementCategoryServiceImpl.getAllAdvertisementCategoryByPagination(index, pageSize);
        Long count = advertisementCategoryServiceImpl.getAdvertisementCategoryCount();
		Long pageCount = count/Long.valueOf(pageSize) + 1;

        result.put("advertisementCategories", advertisementCategories);
        result.put("index", index);
		result.put("pageCount", pageCount);

        return JsonUtil.convertObjectToJson(result);
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    @ResponseBody
    public String deleteAdvertisementCategoryById(@RequestParam("id") String id) {
	    boolean result = advertisementCategoryServiceImpl.deleteAdvertisementCategoryById(id);

        return JsonUtil.convertObjectToJson(result);
    }
}
