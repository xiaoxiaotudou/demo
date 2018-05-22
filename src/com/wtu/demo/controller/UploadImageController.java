package com.wtu.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/upload")
public class UploadImageController extends BaseController {

	@ResponseBody
    @RequestMapping(value="/image",method = RequestMethod.POST)
    public String uploadImage(@RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest request, HttpServletResponse response){

		return null;
	}
}
