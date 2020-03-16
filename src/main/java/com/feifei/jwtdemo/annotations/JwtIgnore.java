package com.feifei.jwtdemo.annotations;

import java.lang.annotation.*;

/**
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JwtIgnore {
}