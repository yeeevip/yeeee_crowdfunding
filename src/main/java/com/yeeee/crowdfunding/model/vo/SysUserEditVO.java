package com.yeeee.crowdfunding.model.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 19:55
 */
@Data
@Accessors(chain = true)
public class SysUserEditVO {

    @ApiModelProperty("ID")
    @NotNull(message = "ID不能空", groups = {Edit.class, Info.class})
    private Integer id;

    @NotNull(message = "ID不能空", groups = {Del.class})
    @Size(min = 1, message = "ID不能空", groups = {Del.class})
    private List<Integer> ids;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能空", groups = {Base.class})
    private String username;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("手机")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("角色ID")
    @NotNull(groups = {Base.class}, message = "角色ID不能空")
    @Size(min = 1, groups = {Base.class}, message = "角色ID不能空")
    private Set<Integer> roleIds;

    @ApiModelProperty("组织ID")
    private Set<Integer> orgIds;

    public interface Base {}
    public interface Add extends Base {}
    public interface Edit extends Base {}
    public interface Info {}
    public interface Del {}

}
