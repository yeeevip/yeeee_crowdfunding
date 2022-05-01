package com.yeeee.crowdfunding.mapper;

import com.yeeee.crowdfunding.model.entity.ProvinceCityDistrict;

import java.util.List;

/**
 * create by yeah.一页 2022/05/01 13:12:33
 */
public interface ProvinceCityDistrictMapper {
       List<ProvinceCityDistrict> getList(ProvinceCityDistrict provinceCityDistrict);
       ProvinceCityDistrict getOne(ProvinceCityDistrict provinceCityDistrict);
       int insert(ProvinceCityDistrict provinceCityDistrict);
       int updateByPrimaryKey(ProvinceCityDistrict provinceCityDistrict);
       int batchInsert(List<ProvinceCityDistrict> provinceCityDistrictList);
}




