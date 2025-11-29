package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.ReceiveInformation;
import com.yeeee.crowdfunding.model.vo.ReceiveInfoVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/1 23:18
 */
public class ReceiveInfoConvert {

    public static ReceiveInfoVO entity2VO(ReceiveInformation receiveInformation) {
        if (receiveInformation == null) {
            return null;
        }
        ReceiveInfoVO vo = new ReceiveInfoVO();
        vo.setId(receiveInformation.getId());
        vo.setReceiver(receiveInformation.getReceiver());
        vo.setPhone(receiveInformation.getPhone());
        vo.setAddress(receiveInformation.getAddress());
        vo.setSetDefault(receiveInformation.getSetDefault());
        return vo;
    }

    public static ReceiveInformation vo2Entity(ReceiveInfoVO receiveInfoVO) {
        if (receiveInfoVO == null) {
            return null;
        }
        ReceiveInformation entity = new ReceiveInformation();
        entity.setId(receiveInfoVO.getId());
        entity.setReceiver(receiveInfoVO.getReceiver());
        entity.setPhone(receiveInfoVO.getPhone());
        entity.setAddress(receiveInfoVO.getAddress());
        entity.setSetDefault(receiveInfoVO.getSetDefault());
        return entity;
    }

}
