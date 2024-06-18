package com.yeeee.crowdfunding.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InitiatorCompanyInfo {

    private Integer id;

    private Integer projectId;

    private String firmName;

    private String businessNumber;

    private String slanderName;

    private String address;

    private String contactName;

    private String contactPhone;

    private String licensePic;

    private String registeredNumPic;

    private String taxPig;
}