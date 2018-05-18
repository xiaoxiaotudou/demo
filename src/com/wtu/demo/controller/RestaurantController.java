package com.wtu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtu.demo.model.Restaurant;
import com.wtu.demo.service.impl.RestaurantServiceImpl;
import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping(value="/restaurant")
public class RestaurantController extends BaseController {

	@Autowired
	private RestaurantServiceImpl restaurantServiceImpl;

	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	@ResponseBody
	public String getAllRestaurant(@RequestParam("index") String index,
			@RequestParam("pageSize") String pageSize) {
		List<Restaurant> restaurants = restaurantServiceImpl.getAllRestaurant(index, pageSize);

		return JsonUtil.convertObjectToJson(restaurants);
	}
}
