package com.wtu.demo.service;

public interface AdvertisementService {
    public boolean createAdvertisement(String categoryId, String weight, String description, String detail);
    public String getAdvertisementById(String pkId);
}