package com.feifei.jwtdemo.interceptors;

import com.feifei.jwtdemo.annotations.JwtIgnore;
import com.feifei.jwtdemo.config.Audience;
import com.feifei.jwtdemo.config.JwtTokenUtil;
import com.feifei.jwtdemo.config.MyRequestContextHolder;
import com.feifei.jwtdemo.config.UserInfo;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * jwt校验拦截器
 *
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    Audience audience;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 忽略掉带有JwtIgnore注解的请求，无需进行token校验
        if (handler instanceof HandlerMethod) {
            // 获取处理方法，也就是action的方法
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore methodAnnotation = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (Objects.nonNull(methodAnnotation)) {
                return true;
            }
        }

        // 解析jwt,options是用来获取服务器支持哪些http方式的请求，因此不做拦截
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        // 获取请求头中的Authorization认证jwt token信息
        String authorization = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        log.info("获取到authorization = {}", authorization);
        // 校验authorization格式是否正确
        if (StringUtils.isBlank(authorization) || !authorization.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            log.info("=== 用户未登录，请先登录 ===");
            throw new RuntimeException("用户未登录，请先登录");
        }

        // 解析token
        String token = authorization.substring(JwtTokenUtil.TOKEN_PREFIX.length() - 1);
        if (Objects.isNull(audience)) {
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
            audience = Objects.requireNonNull(webApplicationContext).getBean(Audience.class);
        }

        // 验签, 解析失败则报错
        UserInfo userInfo = JwtTokenUtil.getAll(token, audience.getBase64Secret());
        // 将用户数据添加到threadLocal中
        MyRequestContextHolder.add(userInfo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 会话结束后移除threadLocal中的用户信息
        MyRequestContextHolder.remove();
    }
}