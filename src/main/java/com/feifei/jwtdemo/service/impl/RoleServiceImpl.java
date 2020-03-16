package com.feifei.jwtdemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.feifei.jwtdemo.event.RegisterEvent;
import com.feifei.jwtdemo.event.RegisterEventEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feifei.jwtdemo.mapper.RoleMapper;
import com.feifei.jwtdemo.model.Role;
import com.feifei.jwtdemo.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

import static com.feifei.jwtdemo.model.Role.COL_ROLE_NAME;

/**
 * 
 *
 * @author shixiongfei
 * @date 2020-03-16
 * @since 
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService, ApplicationListener<RegisterEvent> {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onApplicationEvent(RegisterEvent event) {
        log.info("用户注册时，角色服务进行事件处理");
        RegisterEventEntity entity = (RegisterEventEntity) event.getSource();
        String roleName = entity.getRoleName();
        // 查询角色是否存在
        Role role = query().eq(COL_ROLE_NAME, roleName)
                .one();
        if (Objects.isNull(role)) {
            // 新增
            role = new Role();
            role.setRoleId(IdWorker.getIdStr())
                    .setRoleName(roleName);
            save(role);
        }

        entity.setRoleId(role.getRoleId());
    }
}