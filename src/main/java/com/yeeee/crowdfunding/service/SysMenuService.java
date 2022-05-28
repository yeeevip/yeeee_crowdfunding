package com.yeeee.crowdfunding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeee.crowdfunding.model.entity.SysMenu;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.SysMenuVO;

import java.util.List;
import java.util.Map;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/4 17:07
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenuVO> getMenuListTree();

    PageVO<SysMenuVO> getSysMenuListTreenode(String query);

    Map<String, Object> getMenuAuthz();

}
