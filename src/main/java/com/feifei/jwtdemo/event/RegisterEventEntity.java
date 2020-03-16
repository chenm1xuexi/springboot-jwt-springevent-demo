package com.feifei.jwtdemo.event;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterEventEntity {

    String roleId;

    String roleName;

    String organizationId;

    String organizationName;
}