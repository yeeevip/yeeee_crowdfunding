package com.yeeee.crowdfunding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeee.crowdfunding.model.entity.SysCat;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.SysCatVO;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author https://www.yeee.vip
 * @since 2022-05-29
 */
public interface SysCatService extends IService<SysCat> {

    PageVO<SysCatVO> sysCatPageList(String query);

    PageVO<SysCatVO> sysItemTreeList(String query);

    boolean sysCatExist(String query);

    Void addSysCat(SysCatVO editVO);

    Void editSysCat(SysCatVO editVO);

    SysCatVO sysCatInfo(SysCatVO editVO);

    Void delSysCat(SysCatVO editVO);

}
