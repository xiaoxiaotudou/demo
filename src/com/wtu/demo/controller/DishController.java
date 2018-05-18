package com.wtu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtu.demo.model.Dish;
import com.wtu.demo.service.impl.DishServiceImpl;
import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping(value="/dish")
public class DishController extends BaseController {

	@Autowired
	private DishServiceImpl dishServiceImpl;

	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	@ResponseBody
	public String getAllDishByCategoryId(@RequestParam("categoryId") String categoryId) {
		List<Dish> dishs = dishServiceImpl.getAllDishByCategoryId(categoryId);

		return JsonUtil.convertObjectToJson(dishs);
	}
}