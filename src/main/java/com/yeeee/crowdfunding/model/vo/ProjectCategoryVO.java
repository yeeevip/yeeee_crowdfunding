package com.yeeee.crowdfunding.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ProjectCategoryVO {

    private String categoryName;

}