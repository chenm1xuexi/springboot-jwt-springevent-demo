package com.feifei.jwtdemo.config;


/**
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
public class MyRequestContextHolder {

    public static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    public static UserInfo get() {
        return THREAD_LOCAL.get();
    }

    public static void add(UserInfo userInfo) {
        THREAD_LOCAL.set(userInfo);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

}
