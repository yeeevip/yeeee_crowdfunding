package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 12:30
 */
@Data
public class ProjectPageReqVO {

    private Integer pageNum = 1;

    private Integer pageSize = 16;

    private ProjectVO projectVO;

}
