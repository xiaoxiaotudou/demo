package com.wtu.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtu.demo.dao.impl.AdvertisementDaoImpl;
import com.wtu.demo.model.Advertisement;
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

    @Override
    public Advertisement getAdvertisementById(String pkId) {
        return advertisementDaoImpl.getAdvertisementById(Long.valueOf(pkId));
    }

	@Override
	public List<Advertisement> getAllAdvertisement(String index, String pageSize) {
		return advertisementDaoImpl.getAllAdvertisement(Long.valueOf(index), Long.valueOf(pageSize));
	}

	@Override
	public List<Advertisement> getAllAdvertisementByCategoryId(
			String categoryId, String index, String pageSize) {
		return advertisementDaoImpl.getAllAdvertisementByCategoryId(Long.valueOf(categoryId), Long.valueOf(index), Long.valueOf(pageSize));
	}

    @Override
    public boolean deleteAdvertisementById(String id) {
        return advertisementDaoImpl.deleteAdvertisementById(Long.valueOf(id));
    }

	@Override
	public long getAdvertisementCount() {
		return advertisementDaoImpl.getAdvertisementCount();
	}
}