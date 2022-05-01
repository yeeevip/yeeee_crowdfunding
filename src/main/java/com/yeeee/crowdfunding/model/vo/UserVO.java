package com.yeeee.crowdfunding.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * description......
 *
 * @author yeeee
 * @since 2022/4/30 19:55
 */
@Data
@Accessors(chain = true)
public class UserVO {

    private Integer id;

    @ApiModelProperty("用户名")
    private String username = "";

    private String email = "";

    private Integer sex;

    private String realName = "";

    private String idNumber = "";

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateOfBirth;

    private String age = "";

    private String mobile = "";

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateOfRegistration;

}
