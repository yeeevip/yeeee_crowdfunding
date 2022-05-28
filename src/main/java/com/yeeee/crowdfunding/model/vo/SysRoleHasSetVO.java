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
public class SysRoleHasSetVO {

    private Set<Integer> checkedKeys;

    private List<RoleVO> list;

    @Data
    public static class RoleVO {
        private Integer id;
        private String name;
    }

}
