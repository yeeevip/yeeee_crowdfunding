package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.InitiatorCompanyInfo;
import com.yeeee.crowdfunding.model.entity.InitiatorPersonInfo;
import com.yeeee.crowdfunding.model.vo.InitiatorCompanyInfoVO;
import com.yeeee.crowdfunding.model.vo.InitiatorPersonInfoVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/29 22:25
 */
public class InitiatorInfoVOConvert {

    public static InitiatorPersonInfo personInfoVO2Entity(InitiatorPersonInfoVO personInfoVO) {
        if (personInfoVO == null) {
            return null;
        }
        InitiatorPersonInfo entity = new InitiatorPersonInfo();
        entity.setName(personInfoVO.getName());
        entity.setIdNumber(personInfoVO.getIdNumber());
        entity.setPhone(personInfoVO.getPhone());
        entity.setAddress(personInfoVO.getAddress());
        entity.setIdPicFace(personInfoVO.getIdPicFace());
        entity.setIdPicInverse(personInfoVO.getIdPicInverse());
        return entity;
    }

    public static InitiatorPersonInfoVO entity2PersonInfoVO(InitiatorPersonInfo initiatorPersonInfo) {
        if (initiatorPersonInfo == null) {
            return null;
        }
        InitiatorPersonInfoVO vo = new InitiatorPersonInfoVO();
        vo.setName(initiatorPersonInfo.getName());
        vo.setIdNumber(initiatorPersonInfo.getIdNumber());
        vo.setPhone(initiatorPersonInfo.getPhone());
        vo.setAddress(initiatorPersonInfo.getAddress());
        vo.setIdPicFace(initiatorPersonInfo.getIdPicFace());
        vo.setIdPicInverse(initiatorPersonInfo.getIdPicInverse());
        return vo;
    }

    public static InitiatorCompanyInfo companyInfoVO2Entity(InitiatorCompanyInfoVO companyInfoVO) {
        if (companyInfoVO == null) {
            return null;
        }
        InitiatorCompanyInfo entity = new InitiatorCompanyInfo();
        entity.setFirmName(companyInfoVO.getFirmName());
        entity.setBusinessNumber(companyInfoVO.getBusinessNumber());
        entity.setSlanderName(companyInfoVO.getSlanderName());
        entity.setAddress(companyInfoVO.getAddress());
        entity.setContactName(companyInfoVO.getContactName());
        entity.setContactPhone(companyInfoVO.getContactPhone());
        entity.setLicensePic(companyInfoVO.getLicensePic());
        entity.setRegisteredNumPic(companyInfoVO.getRegisteredNumPic());
        entity.setTaxPig(companyInfoVO.getTaxPig());
        return entity;
    }

    public static InitiatorCompanyInfoVO entity2CompanyInfoVO(InitiatorCompanyInfo initiatorCompanyInfo) {
        if (initiatorCompanyInfo == null) {
            return null;
        }
        InitiatorCompanyInfoVO vo = new InitiatorCompanyInfoVO();
        vo.setFirmName(initiatorCompanyInfo.getFirmName());
        vo.setBusinessNumber(initiatorCompanyInfo.getBusinessNumber());
        vo.setSlanderName(initiatorCompanyInfo.getSlanderName());
        vo.setAddress(initiatorCompanyInfo.getAddress());
        vo.setContactName(initiatorCompanyInfo.getContactName());
        vo.setContactPhone(initiatorCompanyInfo.getContactPhone());
        vo.setLicensePic(initiatorCompanyInfo.getLicensePic());
        vo.setRegisteredNumPic(initiatorCompanyInfo.getRegisteredNumPic());
        vo.setTaxPig(initiatorCompanyInfo.getTaxPig());
        return vo;
    }

}
