package com.yeeee.crowdfunding.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/29 21:40
 */
@Data
public class IndexProjectListVO {

    @ApiModelProperty("热门众筹")
    List<ProjectVO> hotList;

    @ApiModelProperty("公益众筹")
    List<ProjectVO> welfareList;

    @ApiModelProperty("农业众筹")
    List<ProjectVO> agList;

    @ApiModelProperty("出版众筹")
    List<ProjectVO> publishList;

    @ApiModelProperty("艺术众筹")
    List<ProjectVO> artList;

}
