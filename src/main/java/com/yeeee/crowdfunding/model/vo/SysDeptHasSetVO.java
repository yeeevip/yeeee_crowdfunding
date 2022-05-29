package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/5/28 22:14
 */
@Data
public class SysDeptHasSetVO {

    private Set<Integer> checkedKeys;

    private List<SysDeptVO> list;

}
