package com.feifei.jwtdemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feifei.jwtdemo.config.Audience;
import com.feifei.jwtdemo.config.JwtTokenUtil;
import com.feifei.jwtdemo.config.MyRequestContextHolder;
import com.feifei.jwtdemo.config.UserInfo;
import com.feifei.jwtdemo.dto.AddUserDTO;
import com.feifei.jwtdemo.dto.UserLoginDTO;
import com.feifei.jwtdemo.event.RegisterEvent;
import com.feifei.jwtdemo.event.RegisterEventEntity;
import com.feifei.jwtdemo.mapper.UserMapper;
import com.feifei.jwtdemo.model.User;
import com.feifei.jwtdemo.service.UserService;
import com.feifei.jwtdemo.utils.Md5Util;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.feifei.jwtdemo.model.User.COL_USERNAME;


/**
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    Audience audience;

    ApplicationEventPublisher eventPublisher;

    @Override
    public String login(UserLoginDTO request) {

        User user = query().eq(COL_USERNAME, request.getUsername())
                .one();

        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 校验密码是否正确
        boolean isTrue = Md5Util.crypt(request.getPassword()).equalsIgnoreCase(user.getPassword());
        if (!isTrue) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 发放签证
        return JwtTokenUtil.createJwt(user, audience);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(AddUserDTO request) {
        // 校验用户名是否重复
        String username = request.getUsername();
        User user = query().eq(COL_USERNAME, username)
                .one();
        if (Objects.nonNull(user)) {
            throw new RuntimeException("用户名已存在");
        }
        // 采用事件驱动进行解耦
        RegisterEventEntity entity = new RegisterEventEntity();
        entity.setRoleName(request.getRoleName())
                .setOrganizationName(request.getOrganizationName());
        eventPublisher.publishEvent(new RegisterEvent(entity));
        // 创建用户信息
        user = new User();
        BeanUtils.copyProperties(request, user);
        user.setRoleId(entity.getRoleId())
                .setOrganizationId(entity.getOrganizationId())
                .setPassword(Md5Util.crypt(request.getPassword()))
                .setUserId(IdWorker.getIdStr());
        save(user);
    }

    @Override
    public String index() {
        UserInfo userInfo = MyRequestContextHolder.get();
        String username = userInfo.getUsername();
        return String.format("hello %s", username);
    }
}
