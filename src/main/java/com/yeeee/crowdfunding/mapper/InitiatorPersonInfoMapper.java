package com.yeeee.crowdfunding.mapper;

import com.yeeee.crowdfunding.model.entity.InitiatorPersonInfo;

import java.util.List;

/**
 * create by yeah.一页 2022/05/01 16:00:34
 */
public interface InitiatorPersonInfoMapper {
       List<InitiatorPersonInfo> getList(InitiatorPersonInfo initiatorPersonInfo);
       InitiatorPersonInfo getOne(InitiatorPersonInfo initiatorPersonInfo);
       int insert(InitiatorPersonInfo initiatorPersonInfo);
       int updateByPrimaryKey(InitiatorPersonInfo initiatorPersonInfo);
       int batchInsert(List<InitiatorPersonInfo> initiatorPersonInfoList);
}




