package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.ProjectRepay;
import com.yeeee.crowdfunding.model.vo.ProjectRepayVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 20:18
 */
public class ProjectRepayConvert {

    public static ProjectRepayVO projectRepay2VO(ProjectRepay projectRepay) {
        if (projectRepay == null) {
            return null;
        }
        ProjectRepayVO vo = new ProjectRepayVO();
        vo.setId(projectRepay.getId());
        vo.setPayTitle(projectRepay.getPayTitle());
        vo.setPayContent(projectRepay.getPayContent());
        vo.setType(projectRepay.getType());
        vo.setTime(projectRepay.getTime());
        vo.setMoney(projectRepay.getMoney());
        return vo;
    }

    public static ProjectRepay vo2Entity(ProjectRepayVO projectRepayVO) {
        if (projectRepayVO == null) {
            return null;
        }
        ProjectRepay entity = new ProjectRepay();
        entity.setId(projectRepayVO.getId());
        entity.setPayTitle(projectRepayVO.getPayTitle());
        entity.setPayContent(projectRepayVO.getPayContent());
        entity.setType(projectRepayVO.getType());
        entity.setTime(projectRepayVO.getTime());
        entity.setMoney(projectRepayVO.getMoney());
        return entity;
    }

}
