package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.ReceiveInformation;
import com.yeeee.crowdfunding.model.vo.ReceiveInfoVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/1 23:18
 */
@Mapper(componentModel = "spring")
public interface ReceiveInfoConvert {

    ReceiveInfoVO entity2VO(ReceiveInformation receiveInformation);

    ReceiveInformation vo2Entity(ReceiveInfoVO receiveInfoVO);

}
