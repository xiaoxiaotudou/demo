package com.wtu.demo.service;

import java.util.List;

import com.wtu.demo.model.Advertisement;

public interface AdvertisementService {
    public boolean createAdvertisement(String categoryId, String weight, String description, String detail);
    public String getAdvertisementDetailById(String pkId);
    public List<Advertisement> getAllAdvertisement(String index, String pageSize);
    public List<Advertisement> getAllAdvertisementByCategoryId(String categoryId, String index, String pageSize);
}