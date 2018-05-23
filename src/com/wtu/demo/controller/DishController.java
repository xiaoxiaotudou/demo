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

import com.wtu.demo.model.Dish;
import com.wtu.demo.service.impl.DishServiceImpl;
import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping(value="/dish")
public class DishController extends BaseController {

	@Autowired
	private DishServiceImpl dishServiceImpl;

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
	    ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/html/dish/index.jsp");

		return modelAndView;
	}

	@RequestMapping(value="/getAllByCategoryId", method=RequestMethod.GET)
	@ResponseBody
	public String getAllDishByCategoryId(@RequestParam("categoryId") String categoryId) {
		List<Dish> dishs = dishServiceImpl.getAllDishByCategoryId(categoryId);

		return JsonUtil.convertObjectToJson(dishs);
	}

	@RequestMapping(value="/getAllDishByPagination", method=RequestMethod.GET)
	@ResponseBody
	public String getAllDishByPagination(@RequestParam("dishCategoryId") String dishCategoryId,
			@RequestParam("index") String index,
			@RequestParam("pageSize") String pageSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Dish> dishs = dishServiceImpl.getAllDishByPagination(dishCategoryId, index, pageSize);
		Long count = dishServiceImpl.getAllDishCountByCategoryId(dishCategoryId);
		Long pageCount = count/Long.valueOf(pageSize) + 1;

        result.put("dishs", dishs);
        result.put("index", index);
		result.put("pageCount", pageCount);

		return JsonUtil.convertObjectToJson(result);
	}

	@RequestMapping(value="/get", method=RequestMethod.GET)
	@ResponseBody
	public String getDishById(@RequestParam("id") String id) {
		Dish dish = dishServiceImpl.getDishById(id);

		return JsonUtil.convertObjectToJson(dish);
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	@ResponseBody
	public String deleteDish(@RequestParam("id") String id) {
		boolean result = dishServiceImpl.deleteDishById(id);
		return JsonUtil.convertObjectToJson(result);
	}

	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public String editDish(@RequestParam("id") String id,
			@RequestParam("dishName") String dishName,
			@RequestParam("dishPrice") String dishPrice,
			@RequestParam("dishImage") String dishImage) {
		boolean result = dishServiceImpl.editDish(id, dishName, dishPrice, dishImage);
		return JsonUtil.convertObjectToJson(result);
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public String createDish(@RequestParam("dishCategoryId") String dishCategoryId,
			@RequestParam("dishName") String dishName,
			@RequestParam("dishPrice") String dishPrice,
			@RequestParam("dishImage") String dishImage) {
		boolean result = dishServiceImpl.createDish(dishCategoryId, dishName, dishPrice, dishImage);
		return JsonUtil.convertObjectToJson(result);
	}

}