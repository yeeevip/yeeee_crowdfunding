
package com.yeeee.crowdfunding.model.enums;

/**
 * <p>
 * 系统资源类型枚举
 * </p>
 *
 * @author https://www.yeee.vip丶一页 (quhailong1995@gmail.com)
 */

public enum SysMenuTypeEnum {

    menu(0, "菜单"),
    func(1, "功能"),
    pseudoMenu(2, "伪菜单");

    private final int code;

    private final String name;

    SysMenuTypeEnum(int cd, String nm) {
        this.code = cd;
        this.name = nm;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
