package com.feifei.jwtdemo.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

/**
 *
 * 用户登录dto
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginDTO {

    @NotBlank(message = "用户名不可为空")
    String username;

    @NotBlank(message = "密码不可为空")
    String password;
}