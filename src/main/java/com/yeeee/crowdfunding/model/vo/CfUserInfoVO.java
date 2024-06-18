package com.yeeee.crowdfunding.model.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

/**
 * description......
 *
 * @author https://www.yeee.vip
 * @since 2022/4/30 19:55
 */
@Data
@Accessors(chain = true)
public class CfUserInfoVO {

    private Integer id;

    @ApiModelProperty("用户名")
    private String username = "";

    private String nickName = "";

    private String email = "";

    private Integer sex;

    private String realName = "";

    private String idNumber = "";

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = DatePattern.NORM_DATE_PATTERN)
    private Date dateOfBirth;

    private String age = "";

    private String mobile = "";

    private String phone = "";

    private String city = "";

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateOfRegistration;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
