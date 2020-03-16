package com.feifei.jwtdemo.event;

import org.springframework.context.ApplicationEvent;

/**
 * 注册事件
 *
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
public class RegisterEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public RegisterEvent(Object source) {
        super(source);
    }
}
