package com.yeeee.crowdfunding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.yeeee.crowdfunding.convert.SysCatConvert;
import com.yeeee.crowdfunding.exception.BizException;
import com.yeeee.crowdfunding.mapper.SysCatMapper;
import com.yeeee.crowdfunding.model.entity.SysCat;
import com.yeeee.crowdfunding.model.vo.PageVO;
import com.yeeee.crowdfunding.model.vo.SysCatVO;
import com.yeeee.crowdfunding.service.SysCatService;
import com.yeeee.crowdfunding.utils.wrapper.MyPageWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author https://www.yeee.vip
 * @since 2022-05-29
 */
@RequiredArgsConstructor
@Service
public class SysCatServiceImpl extends ServiceImpl<SysCatMapper, SysCat> implements SysCatService {

    private final SysCatConvert sysCatConvert;

    @Override
    public PageVO<SysCatVO> sysCatPageList(String query) {
        MyPageWrapper<SysCat> pageWrapper = new MyPageWrapper<>(query);
        IPage<SysCat> page = this.page(pageWrapper.getPage(), pageWrapper.getQueryWrapper());
        List<SysCatVO> catVOList = page.getRecords()
                .stream()
                .map(sysCatConvert::entity2VO)
                .collect(Collectors.toList());
        return new PageVO<>((int)page.getCurrent(), (int)page.getSize(), (int)page.getPages(), page.getTotal(), catVOList);
    }

    @Override
    public PageVO<SysCatVO> sysItemTreeList(String query) {
        MyPageWrapper<SysCat> pageWrapper = new MyPageWrapper<>(query);
        QueryWrapper<SysCat> queryWrapper = pageWrapper.getQueryWrapper();
        List<SysCat> sysCatList = this.list(queryWrapper);
        List<SysCatVO> catVOList = sysCatList
                .stream()
                .map(sysCatConvert::entity2VO)
                .collect(Collectors.toList());
        Map<Integer, List<SysCatVO>> pidMap = catVOList.stream().filter(item -> item.getPid() != null && item.getPid() !=-1).collect(Collectors.groupingBy(SysCatVO::getPid));
        List<SysCatVO> rootList = Lists.newArrayList();
        catVOList.forEach(item -> {
            if (item.getPid() == null || item.getPid() == -1) {
                this.tree(pidMap, item);
                rootList.add(item);
            }
        });
        return new PageVO<>(0, 0, 0, 0L, rootList);
    }

    private void tree(Map<Integer, List<SysCatVO>> pidMap, SysCatVO curCat) {
        List<SysCatVO> child = pidMap.get(curCat.getId());
        if (child == null) return;
        curCat.setChildren(child);
        child.forEach(item -> {
            tree(pidMap, item);
        });
    }

    @Override
    public boolean sysCatExist(String query) {
        MyPageWrapper<SysCat> pageWrapper = new MyPageWrapper<>(query);
        return this.count(pageWrapper.getQueryWrapper()) > 0;
    }

    @Override
    public Void addSysCat(SysCatVO editVO) {
        SysCat sysCat = sysCatConvert.vo2Entity(editVO);
        this.save(sysCat);
        return null;
    }

    @Override
    public Void editSysCat(SysCatVO editVO) {
        SysCat sysCat = this.getById(editVO.getId());
        if (sysCat == null) {
            throw new BizException("字典不存在");
        }
        SysCat upd = sysCatConvert.vo2Entity(editVO);
        this.updateById(upd);
        return null;
    }

    @Override
    public SysCatVO sysCatInfo(SysCatVO editVO) {
        SysCat sysCat = this.getById(editVO.getId());
        if (sysCat == null) {
            throw new BizException("字典不存在");
        }
        SysCatVO sysCatVO = sysCatConvert.entity2VO(sysCat);
        if (sysCatVO.getPid() != null) {
            SysCat pCat = this.getById(sysCatVO.getPid());
            sysCatVO.setPnm(Optional.ofNullable(pCat).orElseGet(SysCat::new).getName());
        }
        return sysCatVO;
    }

    @Override
    public Void delSysCat(SysCatVO editVO) {
        this.removeByIds(editVO.getIds());
        return null;
    }
}
