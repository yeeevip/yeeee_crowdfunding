package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.SysCat;
import com.yeeee.crowdfunding.model.vo.SysCatVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/29 11:36
 */
@Mapper(componentModel = "spring")
public interface SysCatConvert {

    SysCatVO entity2VO(SysCat sysCat);

    SysCat vo2Entity(SysCatVO sysCatVO);

}
