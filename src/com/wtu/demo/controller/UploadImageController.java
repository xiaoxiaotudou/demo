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
@RequestMapping(value="/resource/upload")  
public class UploadImageController extends BaseController {
	
	@ResponseBody  
    @RequestMapping(value="/images",method = RequestMethod.POST)  
    public String uploadImage(@RequestParam(value = "file", required = false) MultipartFile file,
    							HttpServletRequest request,
								HttpServletResponse response){
        System.out.println("Upload");
        
        return "123";
	}
}
