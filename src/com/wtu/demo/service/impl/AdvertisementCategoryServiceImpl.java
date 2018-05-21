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
    public boolean createAdvertisementCategory(String categoryName) {
        return advertisementCategoryDaoImpl.createAdvertisementCategory(categoryName);
    }

    @Override
    public boolean editAdvertisementCategoryById(String id, String categoryName) {
        return advertisementCategoryDaoImpl.editAdvertisementCategoryById(Long.valueOf(id), categoryName);
    }


    @Override
    public AdvertisementCategory getAdvertisementCategoryById(String id) {
        return advertisementCategoryDaoImpl.getAdvertisementCategoryById(Long.valueOf(id));
    }

    @Override
    public List<AdvertisementCategory> getAllAdvertisementCategory() {
        return advertisementCategoryDaoImpl.getAllAdvertisementCategory();
    }

    @Override
    public List<AdvertisementCategory> getAllAdvertisementCategoryByPagination(String index, String pageSize) {
        return advertisementCategoryDaoImpl.getAllAdvertisementCategoryByPagination(Long.valueOf(index), Long.valueOf(pageSize));
    }

    @Override
    public boolean deleteAdvertisementCategoryById(String id) {
        return advertisementCategoryDaoImpl.deleteAdvertisementCategoryById(Long.valueOf(id));
    }

    public long getAdvertisementCategoryCount() {
    	return advertisementCategoryDaoImpl.getAdvertisementCategoryCount();
    }
}
