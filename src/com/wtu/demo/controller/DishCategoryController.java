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

import com.wtu.demo.model.DishCategory;
import com.wtu.demo.service.impl.DishCategoryServiceImpl;
import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping(value="/dishCategory")
public class DishCategoryController extends BaseController {

	@Autowired
	private DishCategoryServiceImpl dishCategoryServiceImpl;

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
	    ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/html/dishcategory/index.jsp");

		return modelAndView;
	}

	@RequestMapping(value="/getAllByPagination", method=RequestMethod.GET)
    @ResponseBody
    public String getAllDishCategoryByPagination(@RequestParam("index") String index,
			@RequestParam("pageSize") String pageSize,
			@RequestParam("restaurantId") String restaurantId) {
    	Map<String, Object> result = new HashMap<String, Object>();
        List<DishCategory> dishCategories = dishCategoryServiceImpl.getAllDishCategoryByPagination(index, pageSize, restaurantId);
        Long count = dishCategoryServiceImpl.getDishCategoryCount();
		Long pageCount = count/Long.valueOf(pageSize) + 1;

        result.put("dishCategories", dishCategories);
        result.put("index", index);
		result.put("pageCount", pageCount);

        return JsonUtil.convertObjectToJson(result);
    }

	@RequestMapping(value="/get", method=RequestMethod.GET)
    @ResponseBody
    public String getDishCategoryById(@RequestParam("id") String id) {
		DishCategory dishCategory = dishCategoryServiceImpl.getDishCategoryById(id);

        return JsonUtil.convertObjectToJson(dishCategory);
    }

	@RequestMapping(value="/delete", method=RequestMethod.GET)
    @ResponseBody
    public String deleteDishCategoryById(@RequestParam("id") String id) {
		boolean result = dishCategoryServiceImpl.deleteDishCategoryById(id);

        return JsonUtil.convertObjectToJson(result);
    }

	@RequestMapping(value="/getAllByRestaurantId", method=RequestMethod.GET)
	@ResponseBody
	public String getAllDishCategoryByRestaurantId(@RequestParam("restaurantId") String restaurantId) {
		List<DishCategory> dishCategories = dishCategoryServiceImpl.getAllDishCategoryByRestaurantId(restaurantId);

		return JsonUtil.convertObjectToJson(dishCategories);
	}
}
