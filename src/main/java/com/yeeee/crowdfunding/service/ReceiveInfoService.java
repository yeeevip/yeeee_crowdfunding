package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.ReceiveInfoVO;
import com.yeeee.crowdfunding.model.vo.ReceivePageReqVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/7 23:07
 */
public interface ReceiveInfoService {
    PageVO<ReceiveInfoVO> getReceivePageList(ReceivePageReqVO receivePageReqVO);

    Void updateReceiveInfo(ReceiveInfoVO receiveInfoVO);

    Void addReceiveInfo(ReceiveInfoVO receiveInfoVO);

}
