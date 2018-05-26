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

import com.wtu.demo.model.User;
import com.wtu.demo.service.impl.UserServiceImpl;
import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
	    ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/html/user/index.jsp");

		return modelAndView;
	}

    @RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/html/user/create.jsp");

		return modelAndView;
	}

	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView editPage(@RequestParam("userId") String userId){
        ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/html/user/create.jsp");
		modelAndView.addObject("userId", userId);

		return modelAndView;
	}

	@RequestMapping(value="create", method=RequestMethod.POST)
	@ResponseBody
	public String createUser(@RequestParam("userName") String userName,
			@RequestParam("tel") String tel,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("gender") String gender,
			@RequestParam("password") String password) {
		boolean result = userServiceImpl.createUser(userName, tel, firstName, lastName, gender, password);

		return JsonUtil.convertObjectToJson(result);
	}

	@RequestMapping(value="edit", method=RequestMethod.POST)
	@ResponseBody
	public String editUser(@RequestParam("id") String id,
			@RequestParam("tel") String tel,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("gender") String gender,
			@RequestParam("password") String password) {
		boolean result = userServiceImpl.editUser(id, tel, firstName, lastName, gender, password);

		return JsonUtil.convertObjectToJson(result);
	}

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
	@ResponseBody
	public String signin(@RequestParam("email") String email, @RequestParam("password") String password) {
    	return JsonUtil.convertObjectToJson(userServiceImpl.checkSignIn(email, password));
    }

    @RequestMapping(value="/getAllUserByPagination", method=RequestMethod.GET)
	@ResponseBody
    public String getAllUserByPagination(@RequestParam("index") String index,
			@RequestParam("pageSize") String pageSize) {
    	Map<String, Object> result = new HashMap<String, Object>();
		List<User> users = userServiceImpl.getAllUserByPagination(index, pageSize);
		Long count = userServiceImpl.geyAllUserCount();
		Long pageCount = count%Long.valueOf(pageSize) == 0 ? count/Long.valueOf(pageSize) : count/Long.valueOf(pageSize) + 1;

		result.put("users", users);
		result.put("index", index);
		result.put("pageCount", pageCount);

		return JsonUtil.convertObjectToJson(result);
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
	@ResponseBody
    public String deleteUserById(@RequestParam("id") String id) {
    	boolean result = userServiceImpl.deleteUserById(id);

		return JsonUtil.convertObjectToJson(result);
    }

    @RequestMapping(value="/getUserById", method=RequestMethod.GET)
	@ResponseBody
    public String getUserById(@RequestParam("id") String id) {
    	User user = userServiceImpl.getUserById(id);

		return JsonUtil.convertObjectToJson(user);
    }
}
