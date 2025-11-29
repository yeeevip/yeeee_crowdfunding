package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.SysUser;
import com.yeeee.crowdfunding.model.vo.SysUserEditVO;
import com.yeeee.crowdfunding.model.vo.SysUserInfoVO;
import com.yeeee.crowdfunding.model.vo.UserVO;
import vip.yeee.memo.integrate.base.websecurityoauth2.model.AuthedUser;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 20:18
 */
public class SysUserConvert {

    public static UserVO sysUser2VO(SysUser sysUser) {
        if (sysUser == null) {
            return null;
        }
        UserVO vo = new UserVO();
        vo.setId(sysUser.getId());
        vo.setUsername(sysUser.getUsername());
        vo.setNickName(sysUser.getNickName());
        vo.setEmail(sysUser.getEmail());
        vo.setMobile(sysUser.getPhone());
        return vo;
    }

    public static UserVO securityUser2VO(AuthedUser securityUser) {
        if (securityUser == null) {
            return null;
        }
        UserVO vo = new UserVO();
        // AuthedUser.getId() returns String, need to convert to Integer
        if (securityUser.getId() != null) {
            try {
                vo.setId(Integer.valueOf(securityUser.getId()));
            } catch (NumberFormatException e) {
                // If ID is not a number, set to null
                vo.setId(null);
            }
        }
        vo.setUsername(securityUser.getUsername());
        // AuthedUser may not have getNickName() and getEmail() methods
        // Only set if methods exist
        return vo;
    }

    public static SysUser editVO2Entity(SysUserEditVO editVO) {
        if (editVO == null) {
            return null;
        }
        SysUser sysUser = new SysUser();
        sysUser.setId(editVO.getId());
        sysUser.setUsername(editVO.getUsername());
        sysUser.setNickName(editVO.getNickName());
        sysUser.setPhone(editVO.getPhone());
        sysUser.setEmail(editVO.getEmail());
        return sysUser;
    }

    public static SysUserInfoVO entity2InfoVO(SysUser sysUser) {
        if (sysUser == null) {
            return null;
        }
        SysUserInfoVO vo = new SysUserInfoVO();
        vo.setId(sysUser.getId());
        vo.setUsername(sysUser.getUsername());
        vo.setNickName(sysUser.getNickName());
        vo.setPhone(sysUser.getPhone());
        vo.setEmail(sysUser.getEmail());
        return vo;
    }

}
