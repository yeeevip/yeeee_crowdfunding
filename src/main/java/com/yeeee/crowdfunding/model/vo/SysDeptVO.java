package com.yeeee.crowdfunding.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/5/28 18:23
 */
@Data
public class SysDeptVO {

    @NotNull(message = "ID不能为空", groups = {Edit.class, Info.class})
    private Integer id;

    private Integer pid;

    private String pnm;

    @NotNull(message = "IDS不能为空", groups = {Del.class})
    @Size(min = 1, message = "IDS不能为空", groups = {Del.class})
    private List<Integer> ids;

    @NotBlank(message = "code不能为空", groups = {Base.class})
    private String code;

    @NotBlank(message = "name不能为空", groups = {Base.class})
    private String name;

    private Integer sort;

    private String remark;

    private List<SysDeptVO> children;

    public interface Base {}
    public interface Add extends Base {}
    public interface Edit extends Base {}
    public interface Info {}
    public interface Del {}

}
