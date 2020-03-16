package com.feifei.jwtdemo.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Accessors(chain = true)
public class UserInfo {

    String userId;

    String username;

    String organizationId;

    String roleId;
}