package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.User;
import com.yeeee.crowdfunding.model.vo.CfUserInfoVO;
import com.yeeee.crowdfunding.model.vo.UserVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 20:18
 */
public class UserConvert {

    public static UserVO user2VO(User user) {
        if (user == null) {
            return null;
        }
        UserVO vo = new UserVO();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setNickName(user.getNickName());
        vo.setEmail(user.getEmail());
        vo.setSex(user.getSex());
        vo.setRealName(user.getRealName());
        vo.setIdNumber(user.getIdNumber());
        vo.setDateOfBirth(user.getDateOfBirth());
        vo.setMobile(user.getMobile());
        vo.setCity(user.getCity());
        vo.setDateOfRegistration(user.getDateOfRegistration());
        return vo;
    }

    public static CfUserInfoVO user2InfoVO(User user) {
        if (user == null) {
            return null;
        }
        CfUserInfoVO vo = new CfUserInfoVO();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setNickName(user.getNickName());
        vo.setEmail(user.getEmail());
        vo.setSex(user.getSex());
        vo.setRealName(user.getRealName());
        vo.setIdNumber(user.getIdNumber());
        vo.setDateOfBirth(user.getDateOfBirth());
        vo.setMobile(user.getMobile());
        vo.setCity(user.getCity());
        vo.setDateOfRegistration(user.getDateOfRegistration());
        return vo;
    }

    public static User vo2Entity(UserVO userVO) {
        if (userVO == null) {
            return null;
        }
        User user = new User();
        user.setId(userVO.getId());
        user.setUsername(userVO.getUsername());
        user.setNickName(userVO.getNickName());
        user.setEmail(userVO.getEmail());
        user.setSex(userVO.getSex());
        user.setRealName(userVO.getRealName());
        user.setIdNumber(userVO.getIdNumber());
        user.setDateOfBirth(userVO.getDateOfBirth());
        user.setMobile(userVO.getMobile());
        user.setCity(userVO.getCity());
        user.setDateOfRegistration(userVO.getDateOfRegistration());
        return user;
    }

}
