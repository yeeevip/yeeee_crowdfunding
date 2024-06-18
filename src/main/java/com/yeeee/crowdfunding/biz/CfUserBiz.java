package com.yeeee.crowdfunding.biz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.UserConvert;
import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.model.entity.User;
import com.yeeee.crowdfunding.model.request.CfUserEditRequest;
import com.yeeee.crowdfunding.model.request.IdsRequest;
import com.yeeee.crowdfunding.model.vo.CfUserInfoVO;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.UserVO;
import com.yeeee.crowdfunding.service.UserService;
import com.yeeee.crowdfunding.service.impl.UserAuthService;
import com.yeeee.crowdfunding.utils.wrapper.MyPageWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2023/2/6 20:20
 */
@Component
public class CfUserBiz {

    @Resource
    private UserService userService;
    @Resource
    private UserConvert userConvert;
    @Resource
    private UserAuthService userAuthService;

    public PageVO<UserVO> cfUserPageList(String query) {
        MyPageWrapper<User> wrapper = new MyPageWrapper<>(query);
        IPage<User> page = userService.page(wrapper.getPage(), wrapper.getQueryWrapper());
        List<UserVO> userVOList = Optional
                .ofNullable(page.getRecords())
                .orElseGet(Lists::newArrayList)
                .stream()
                .map(userConvert::user2VO)
                .collect(Collectors.toList());
        return new PageVO<>((int)page.getCurrent(), (int)page.getSize(), (int)page.getPages(), page.getTotal(), userVOList);
    }

    public Boolean cfUserExist(String query) {
        MyPageWrapper<User> pageWrapper = new MyPageWrapper<>(query);
        return userService.count(pageWrapper.getQueryWrapper()) > 0;
    }

    public Void addCfUser(CfUserEditRequest request) {
        User save = new User();
        BeanUtils.copyProperties(request, save);
        save.setPassword(userAuthService.encodePassword(StrUtil.emptyToDefault(request.getPassword(), "111111")));
        userService.save(save);
        return null;
    }

    public Void editCfUser(CfUserEditRequest request) {
        User user = userService.getById(request.getId());
        if (user == null) {
            throw new BizException("用户不存在");
        }
        User upd = new User();
        BeanUtils.copyProperties(request, upd);
        upd.setId(request.getId());
        if (StrUtil.isNotBlank(request.getPassword())) {
            String encodePassword = userAuthService.encodePassword(request.getPassword());
            if (!request.getPassword().equals(user.getPassword())) {
                upd.setPassword(encodePassword);
            }
        }
        userService.updateById(upd);
        return null;
    }

    public CfUserInfoVO cfUserInfo(CfUserEditRequest request) {
        User user = userService.getById(request.getId());
        if (user == null) {
            throw new BizException("用户不存在");
        }
        return userConvert.user2InfoVO(user);
    }

    public Void delCfUser(IdsRequest request) {
        if (CollectionUtil.isEmpty(request.getIds())) {
            return null;
        }
        userService.removeByIds(request.getIds());
        return null;
    }
}
