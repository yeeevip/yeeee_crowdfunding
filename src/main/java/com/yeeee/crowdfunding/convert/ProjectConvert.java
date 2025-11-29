package com.yeeee.crowdfunding.convert;

import com.yeeee.crowdfunding.model.entity.Project;
import com.yeeee.crowdfunding.model.vo.LunchProjectVO;
import com.yeeee.crowdfunding.model.vo.ProjectDetailVO;
import com.yeeee.crowdfunding.model.vo.ProjectVO;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/29 22:25
 */
public class ProjectConvert {

    public static ProjectVO project2VO(Project project) {
        if (project == null) {
            return null;
        }
        ProjectVO vo = new ProjectVO();
        vo.setId(project.getId());
        vo.setProjectType(project.getCategoryId());
        vo.setTitle(project.getTitle());
        vo.setUserId(project.getUserId());
        vo.setBlurb(project.getBlurb());
        vo.setCoverPath(project.getCoverPath());
        vo.setLaunchDateRaising(project.getLaunchDateRaising());
        vo.setTotalFundRaising(project.getTotalFundRaising());
        vo.setDaysRaising(project.getDaysRaising());
        vo.setHasFundRaising(project.getHasFundRaising());
        vo.setHasAudits(project.getHasAudits());
        vo.setHasFinish(project.getHasFinish());
        vo.setHasDown(project.getHasDown());
        vo.setKeyword(project.getKeyword());
        return vo;
    }

    public static ProjectDetailVO project2DetailVO(Project project) {
        if (project == null) {
            return null;
        }
        ProjectDetailVO vo = new ProjectDetailVO();
        vo.setId(project.getId());
        vo.setProjectType(project.getCategoryId());
        vo.setTitle(project.getTitle());
        vo.setBlurb(project.getBlurb());
        vo.setCoverPath(project.getCoverPath());
        vo.setLaunchDateRaising(project.getLaunchDateRaising());
        vo.setTotalFundRaising(project.getTotalFundRaising());
        vo.setHasFundRaising(project.getHasFundRaising());
        vo.setHasAudits(project.getHasAudits());
        vo.setHasFinish(project.getHasFinish());
        return vo;
    }

    public static Project lunchProjectVOProject(LunchProjectVO lunchProjectVO) {
        if (lunchProjectVO == null) {
            return null;
        }
        Project project = new Project();
        project.setCategoryId(lunchProjectVO.getProjectType());
        project.setCoverPath(lunchProjectVO.getCoverPath());
        project.setTitle(lunchProjectVO.getTitle());
        project.setBlurb(lunchProjectVO.getBlurb());
        project.setTotalFundRaising(lunchProjectVO.getTotalFundRaising());
        project.setDaysRaising(lunchProjectVO.getDaysRaising());
        project.setShenfen(lunchProjectVO.getShenfen());
        return project;
    }

    public static LunchProjectVO project2LunchProjectVO(Project project) {
        if (project == null) {
            return null;
        }
        LunchProjectVO vo = new LunchProjectVO();
        vo.setProjectType(project.getCategoryId());
        vo.setCoverPath(project.getCoverPath());
        vo.setTitle(project.getTitle());
        vo.setBlurb(project.getBlurb());
        vo.setTotalFundRaising(project.getTotalFundRaising());
        vo.setDaysRaising(project.getDaysRaising());
        vo.setShenfen(project.getShenfen());
        return vo;
    }

}
