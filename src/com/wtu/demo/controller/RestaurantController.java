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

import com.wtu.demo.model.Restaurant;
import com.wtu.demo.service.impl.RestaurantServiceImpl;
import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping(value="/restaurant")
public class RestaurantController extends BaseController {

	@Autowired
	private RestaurantServiceImpl restaurantServiceImpl;

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
	    ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/html/restaurant/index.jsp");

		return modelAndView;
	}

	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/html/restaurant/create.jsp");

		return modelAndView;
	}

	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView editPage(@RequestParam("restaurantId") String restaurantId){
        ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/html/restaurant/create.jsp");
		modelAndView.addObject("restaurantId", restaurantId);

		return modelAndView;
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public String createRestaurant(@RequestParam("name") String name,
	        @RequestParam("address") String address,
	        @RequestParam("description") String description,
	        @RequestParam("image") String image) {
		boolean result = restaurantServiceImpl.createRestaurant(name, address, description, image);

		return JsonUtil.convertObjectToJson(result);
	}

	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public String editRestaurant(@RequestParam("id") String id,
			@RequestParam("name") String name,
	        @RequestParam("address") String address,
	        @RequestParam("description") String description,
	        @RequestParam("image") String image) {
		boolean result = restaurantServiceImpl.editRestaurantById(id, name, address, description, image);

		return JsonUtil.convertObjectToJson(result);
	}

	@RequestMapping(value="/getAllByPagination", method=RequestMethod.GET)
	@ResponseBody
	public String getAllRestaurantByPagination(@RequestParam("index") String index,
			@RequestParam("pageSize") String pageSize) {
		Map<String, Object> result =  new HashMap<String, Object>();
		List<Restaurant> restaurants = restaurantServiceImpl.getAllRestaurantByPagination(index, pageSize);
		Long count = restaurantServiceImpl.getRestaurantCount();
		Long pageCount = count/Long.valueOf(pageSize) + 1;

		result.put("restaurants", restaurants);
		result.put("index", index);
		result.put("pageCount", pageCount);

		return JsonUtil.convertObjectToJson(result);
	}

	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	@ResponseBody
	public String getAllRestaurant() {
		List<Restaurant> restaurants = restaurantServiceImpl.getAllRestaurant();

		return JsonUtil.convertObjectToJson(restaurants);
	}

	@RequestMapping(value="/get", method=RequestMethod.GET)
	@ResponseBody
	public String getRestaurantById(@RequestParam("id") String id) {
		Restaurant restaurant = restaurantServiceImpl.getRestaurantById(id);

		return JsonUtil.convertObjectToJson(restaurant);
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	@ResponseBody
	public String deleteRestaurant(@RequestParam("id") String id) {
		boolean result = restaurantServiceImpl.deleteRestaurantById(id);

		return JsonUtil.convertObjectToJson(result);
	}
}
