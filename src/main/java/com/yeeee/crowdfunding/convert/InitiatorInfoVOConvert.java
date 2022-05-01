package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.InitiatorCompanyInfo;
import com.yeeee.crowdfunding.model.entity.InitiatorPersonInfo;
import com.yeeee.crowdfunding.model.vo.InitiatorCompanyInfoVO;
import com.yeeee.crowdfunding.model.vo.InitiatorPersonInfoVO;
import org.mapstruct.Mapper;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/29 22:25
 */
@Mapper(componentModel = "spring")
public interface InitiatorInfoVOConvert {

    InitiatorPersonInfo personInfoVO2Entity(InitiatorPersonInfoVO personInfoVO);

    InitiatorPersonInfoVO entity2PersonInfoVO(InitiatorPersonInfo initiatorPersonInfo);

    InitiatorCompanyInfo companyInfoVO2Entity(InitiatorCompanyInfoVO companyInfoVO);

    InitiatorCompanyInfoVO entity2CompanyInfoVO(InitiatorCompanyInfo initiatorCompanyInfo);

}
