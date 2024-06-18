package com.yeeee.crowdfunding.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ProjectCategory {

    private Integer id;

    private String categoryName;

    private String note;

    private Date createDate;

    private Date changeDate;

    private String changePerson;
}