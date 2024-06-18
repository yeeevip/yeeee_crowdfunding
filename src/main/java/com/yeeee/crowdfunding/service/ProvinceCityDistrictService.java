package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.entity.ProvinceCityDistrict;

import java.util.List;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/1 13:15
 */
public interface ProvinceCityDistrictService {

    List<ProvinceCityDistrict> getList(Integer pid);

}
