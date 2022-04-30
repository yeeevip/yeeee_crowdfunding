package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.Order;
import com.yeeee.crowdfunding.model.entity.Project;
import com.yeeee.crowdfunding.model.vo.BuyOrderVO;
import com.yeeee.crowdfunding.model.vo.ProjectVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 22:25
 */
@Mapper(componentModel = "spring")
public interface OrderConvert {

    BuyOrderVO order2VO(Order order);

}
