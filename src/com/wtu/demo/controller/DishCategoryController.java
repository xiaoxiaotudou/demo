package com.wtu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtu.demo.model.Advertisement;
import com.wtu.demo.model.DishCategory;
import com.wtu.demo.service.impl.DishCategoryServiceImpl;
import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping(value="/dishCategory")
public class DishCategoryController extends BaseController {

	@Autowired
	private DishCategoryServiceImpl dishCategoryServiceImpl;

	@RequestMapping(value="/getAllByRestaurantId", method=RequestMethod.GET)
	@ResponseBody
	public String getAllDishCategoryByRestaurantId(@RequestParam("restaurantId") String restaurantId) {
		List<DishCategory> dishCategories = dishCategoryServiceImpl.getAllDishCategoryByRestaurantId(restaurantId);

		return JsonUtil.convertObjectToJson(dishCategories);
	}
}
