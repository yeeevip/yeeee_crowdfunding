package com.yeeee.crowdfunding.mapper;

import com.yeeee.crowdfunding.model.entity.InitiatorCompanyInfo;

import java.util.List;

/**
 * create by yeah.一页 2022/05/01 16:06:47
 */
public interface InitiatorCompanyInfoMapper {
       List<InitiatorCompanyInfo> getList(InitiatorCompanyInfo initiatorCompanyInfo);
       InitiatorCompanyInfo getOne(InitiatorCompanyInfo initiatorCompanyInfo);
       int insert(InitiatorCompanyInfo initiatorCompanyInfo);
       int updateByPrimaryKey(InitiatorCompanyInfo initiatorCompanyInfo);
       int batchInsert(List<InitiatorCompanyInfo> initiatorCompanyInfoList);
}




