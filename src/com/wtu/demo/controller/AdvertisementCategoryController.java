package com.wtu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtu.demo.model.AdvertisementCategory;
import com.wtu.demo.service.impl.AdvertisementCategoryServiceImpl;
import com.wtu.demo.util.JsonUtil;

@Controller
@RequestMapping("/advertisementCategory")
public class AdvertisementCategoryController extends BaseController {

    @Autowired
    private AdvertisementCategoryServiceImpl advertisementCategoryServiceImpl;

    @RequestMapping(value="/getAll", method=RequestMethod.GET)
    @ResponseBody
    public String getAllAdvertisementCategory() {
        List<AdvertisementCategory> advertisementCategories = advertisementCategoryServiceImpl.getAllAdvertisementCategory();

        return JsonUtil.convertObjectToJson(advertisementCategories);
    }
}
