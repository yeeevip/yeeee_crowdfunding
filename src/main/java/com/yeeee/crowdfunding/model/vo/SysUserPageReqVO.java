package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 12:30
 */
@Data
public class SysUserPageReqVO {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private SysUserVO sysUserVO;

}
