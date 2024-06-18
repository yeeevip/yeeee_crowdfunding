package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.TMsg;
import com.yeeee.crowdfunding.model.vo.TMsgListVO;
import org.springframework.beans.BeanUtils;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2023/2/6 18:37
 */
public class TMsgConvert {
    public static TMsgListVO po2ListVo(TMsg tMsg) {
        TMsgListVO vo = new TMsgListVO();
        if (tMsg != null) {
            BeanUtils.copyProperties(tMsg, vo);
            vo.setId(tMsg.getId().toString());
        }
        return vo;
    }
}
