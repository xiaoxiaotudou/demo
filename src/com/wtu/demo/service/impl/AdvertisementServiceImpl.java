package com.wtu.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtu.demo.dao.impl.AdvertisementDaoImpl;
import com.wtu.demo.service.AdvertisementService;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
	
	@Autowired
	private AdvertisementDaoImpl advertisementDaoImpl;
	
	@Override
	public boolean createAdvertisement(String categoryId, String weight,
			String description, String detail) {
		return advertisementDaoImpl.createAdvertisement(Long.valueOf(categoryId), Double.valueOf(weight), description, detail);
	}
}
