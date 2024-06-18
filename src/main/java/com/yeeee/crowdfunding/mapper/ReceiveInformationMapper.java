package com.yeeee.crowdfunding.mapper;

import com.yeeee.crowdfunding.model.entity.ReceiveInformation;

import java.util.List;

/**
 * create by yeah.一页 2022/05/01 23:13:14
 */
public interface ReceiveInformationMapper {
       List<ReceiveInformation> getList(ReceiveInformation receiveInformation);
       ReceiveInformation getOne(ReceiveInformation receiveInformation);
       int insert(ReceiveInformation receiveInformation);
       int updateByPrimaryKey(ReceiveInformation receiveInformation);
       int batchInsert(List<ReceiveInformation> receiveInformationList);
}




