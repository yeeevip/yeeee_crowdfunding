package com.yeeee.crowdfunding.service;

import com.yeeee.crowdfunding.model.vo.SysMenuVO;

import java.util.List;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/4 17:07
 */
public interface SysMenuService {

    List<SysMenuVO> getMenuListTree();

}
