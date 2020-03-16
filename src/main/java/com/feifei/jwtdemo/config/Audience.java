package com.feifei.jwtdemo.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 *
 *
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Audience {

    String clientId;

    String base64Secret;

    String name;

    int expireSecond;
}