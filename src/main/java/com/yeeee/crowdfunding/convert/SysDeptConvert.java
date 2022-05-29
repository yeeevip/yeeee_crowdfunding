package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.SysDept;
import com.yeeee.crowdfunding.model.vo.SysDeptVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/29 11:36
 */
@Mapper(componentModel = "spring")
public interface SysDeptConvert {

    SysDeptVO entity2VO(SysDept sysDept);

    SysDept vo2Entity(SysDeptVO sysDeptVO);

}
