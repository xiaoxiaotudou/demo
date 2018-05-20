package com.wtu.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtu.demo.dao.impl.AdvertisementCategoryDaoImpl;
import com.wtu.demo.model.AdvertisementCategory;
import com.wtu.demo.service.AdvertisementCategoryService;

@Service
public class AdvertisementCategoryServiceImpl implements
        AdvertisementCategoryService {

    @Autowired
    private AdvertisementCategoryDaoImpl advertisementCategoryDaoImpl;

    @Override
    public List<AdvertisementCategory> getAllAdvertisementCategory() {
        return advertisementCategoryDaoImpl.getAllAdvertisementCategory();
    }
}
