package com.yeeee.crowdfunding.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.ReceiveInfoConvert;
import com.yeeee.crowdfunding.mapper.ReceiveInformationMapper;
import com.yeeee.crowdfunding.model.entity.ReceiveInformation;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.ReceiveInfoVO;
import com.yeeee.crowdfunding.model.vo.ReceivePageReqVO;
import com.yeeee.crowdfunding.service.ReceiveInfoService;
import com.yeeee.crowdfunding.utils.BusinessUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.yeee.memo.integrate.common.websecurity.context.SecurityContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/7 23:08
 */
@Service
@RequiredArgsConstructor
public class ReceiveInfoServiceImpl implements ReceiveInfoService {

    private final ReceiveInformationMapper receiveInformationMapper;

    private final ReceiveInfoConvert receiveInfoConvert;


    @Override
    public PageVO<ReceiveInfoVO> getReceivePageList(ReceivePageReqVO receivePageReqVO) {

        Page<ReceiveInformation> page = PageHelper.startPage(receivePageReqVO.getPageNum(), receivePageReqVO.getPageSize());

        List<ReceiveInformation> informationList = receiveInformationMapper.getList(new ReceiveInformation().setUserId(BusinessUtils.getCurUserId()));
        List<ReceiveInfoVO> infoVOList = Optional.ofNullable(informationList).orElseGet(Lists::newArrayList)
                .stream()
                .map(receiveInfoConvert::entity2VO)
                .collect(Collectors.toList());

        return new PageVO<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), infoVOList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Void updateReceiveInfo(ReceiveInfoVO receiveInfoVO) {

        List<ReceiveInformation> informationList = receiveInformationMapper.getList(new ReceiveInformation().setUserId(BusinessUtils.getCurUserId()).setSetDefault(1));
        if (CollectionUtil.isNotEmpty(informationList)) {
            informationList.forEach(item -> receiveInformationMapper.updateByPrimaryKey(new ReceiveInformation().setId(item.getId()).setSetDefault(0)));
        }

        receiveInformationMapper.updateByPrimaryKey(new ReceiveInformation()
                .setId(receiveInfoVO.getId())
                .setSetDefault(receiveInfoVO.getSetDefault()));

        return null;
    }

    @Override
    public Void addReceiveInfo(ReceiveInfoVO receiveInfoVO) {
        ReceiveInformation save = receiveInfoConvert.vo2Entity(receiveInfoVO);
        save.setUserId(BusinessUtils.getCurUserId());
        receiveInformationMapper.insert(save);
        return null;
    }
}
