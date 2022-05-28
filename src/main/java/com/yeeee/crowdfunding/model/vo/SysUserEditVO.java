package com.yeeee.crowdfunding.model.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 19:55
 */
@Data
@Accessors(chain = true)
public class SysUserEditVO {

    @ApiModelProperty("ID")
    @NotNull(message = "ID不能空", groups = {Edit.class, Info.class, Del.class})
    private Integer id;

    @ApiModelProperty("用户名")
    @NotNull(message = "用户名不能空", groups = {Base.class})
    private String username;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("手机")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("角色ID")
    private Set<Integer> roleIds;

    @ApiModelProperty("组织ID")
    private Set<Integer> orgIds;

    public interface Base {}
    public interface Add extends Base {}
    public interface Edit extends Base {}
    public interface Info {}
    public interface Del {}

}
