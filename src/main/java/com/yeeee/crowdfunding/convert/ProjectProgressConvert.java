package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.ProjectProgress;
import com.yeeee.crowdfunding.model.vo.ProjectProgressVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/29 22:25
 */
public class ProjectProgressConvert {

    public static ProjectProgressVO progress2VO(ProjectProgress projectProgress) {
        if (projectProgress == null) {
            return null;
        }
        ProjectProgressVO vo = new ProjectProgressVO();
        vo.setContent(projectProgress.getContent());
        vo.setPublishDate(projectProgress.getPublishDate());
        vo.setPubUser(projectProgress.getPubUser());
        vo.setProjectId(projectProgress.getProjectId());
        return vo;
    }

}
