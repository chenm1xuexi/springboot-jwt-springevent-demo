package com.feifei.jwtdemo.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * 系统租户信息
 *
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class Tenant {

    /**
     * 组织id
     */
    String organizationId;
}