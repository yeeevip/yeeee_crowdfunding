package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 12:30
 */
@Data
public class ReceivePageReqVO {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private ReceiveInfoVO receiveInfoVO;

}
