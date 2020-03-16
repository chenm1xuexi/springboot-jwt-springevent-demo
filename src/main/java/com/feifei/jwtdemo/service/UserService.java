package com.feifei.jwtdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feifei.jwtdemo.dto.AddUserDTO;
import com.feifei.jwtdemo.dto.UserLoginDTO;
import com.feifei.jwtdemo.model.User;

/**
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-03-16
     * @updateDate 2020-03-16
     * @updatedBy shixiongfei
     */
    String login(UserLoginDTO request);

    /**
     * 注册用户信息
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-03-16
     * @updateDate 2020-03-16
     * @updatedBy shixiongfei
     */
    void register(AddUserDTO request);

    /**
     * 首页
     *
     * @author shixiongfei
     * @date 2020-03-16
     * @updateDate 2020-03-16
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    String index();
}
