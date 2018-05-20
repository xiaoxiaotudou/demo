package com.wtu.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtu.demo.dao.AdvertisementCategoryDao;
import com.wtu.demo.model.AdvertisementCategory;
import com.wtu.demo.util.DBUtil;

@Repository
public class AdvertisementCategoryDaoImpl implements AdvertisementCategoryDao {

    @Override
    public List<AdvertisementCategory> getAllAdvertisementCategory() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<AdvertisementCategory> advertisementCategories = new ArrayList<AdvertisementCategory>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from advertisement_category where deleted = 0");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AdvertisementCategory advertisementCategory = new AdvertisementCategory();

                advertisementCategory.setPkId(resultSet.getLong("pkId"));
                advertisementCategory.setCategoryName(resultSet.getString("categoryName"));

                advertisementCategories.add(advertisementCategory);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return advertisementCategories;
    }
}
