package com.yeeee.crowdfunding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeee.crowdfunding.model.entity.SysDept;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.SysDeptHasSetVO;
import com.yeeee.crowdfunding.model.vo.SysDeptVO;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author https://www.yeee.vip
 * @since 2022-05-29
 */
public interface SysDeptService extends IService<SysDept> {

    PageVO<SysDeptVO> sysDeptPageList(String query);

    boolean sysDeptExist(String query);

    Void addSysDept(SysDeptVO editVO);

    Void editSysDept(SysDeptVO editVO);

    SysDeptVO sysDeptInfo(SysDeptVO editVO);

    Void delSysDept(SysDeptVO editVO);

    SysDeptHasSetVO sysDeptListAndHasSet(Integer userId);

}
