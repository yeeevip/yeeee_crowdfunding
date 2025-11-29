package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.ProjectDetail;
import com.yeeee.crowdfunding.model.vo.ProjectItemVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/29 22:25
 */
public class ProjectDetailConvert {

    public static ProjectItemVO detail2VO(ProjectDetail detail) {
        if (detail == null) {
            return null;
        }
        ProjectItemVO vo = new ProjectItemVO();
        vo.setItemTitle(detail.getItemTitle());
        vo.setItemContent(detail.getItemContent());
        return vo;
    }

    public static ProjectDetail vo2Entity(ProjectItemVO itemVO) {
        if (itemVO == null) {
            return null;
        }
        ProjectDetail detail = new ProjectDetail();
        detail.setItemTitle(itemVO.getItemTitle());
        detail.setItemContent(itemVO.getItemContent());
        return detail;
    }

}
