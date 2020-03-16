package com.feifei.jwtdemo.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

/**
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddUserDTO {

    @NotBlank(message = "用户名不可为空")
    String username;

    @NotBlank(message = "密码不可为空")
    String password;

    @NotBlank(message = "角色不可为空")
    String roleName;

    @NotBlank(message = "组织不可为空")
    String organizationName;
}