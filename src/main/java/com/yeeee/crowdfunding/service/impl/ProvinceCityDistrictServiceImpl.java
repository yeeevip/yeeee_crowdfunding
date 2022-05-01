package com.yeeee.crowdfunding.service.impl;

import com.yeeee.crowdfunding.mapper.ProvinceCityDistrictMapper;
import com.yeeee.crowdfunding.model.entity.ProvinceCityDistrict;
import com.yeeee.crowdfunding.service.ProvinceCityDistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/1 13:15
 */
@RequiredArgsConstructor
@Service
public class ProvinceCityDistrictServiceImpl implements ProvinceCityDistrictService {

    private final ProvinceCityDistrictMapper provinceCityDistrictMapper;


    @Override
    public List<ProvinceCityDistrict> getList(Integer pid) {
        if (pid == null) {
            return Collections.emptyList();
        }
        ProvinceCityDistrict provinceCityDistrict = new ProvinceCityDistrict();
        provinceCityDistrict.setPid(pid);
        return provinceCityDistrictMapper.getList(provinceCityDistrict);
    }
}
