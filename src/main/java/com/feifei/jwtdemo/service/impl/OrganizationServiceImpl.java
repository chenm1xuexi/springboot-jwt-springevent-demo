package com.feifei.jwtdemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.feifei.jwtdemo.event.RegisterEvent;
import com.feifei.jwtdemo.event.RegisterEventEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feifei.jwtdemo.mapper.OrganizationMapper;
import com.feifei.jwtdemo.model.Organization;
import com.feifei.jwtdemo.service.OrganizationService;
import org.springframework.transaction.annotation.Transactional;

import static com.feifei.jwtdemo.model.Organization.COL_ORGANIZATION_NAME;

/**
 * 
 *
 * @author shixiongfei
 * @date 2020-03-16
 * @since 
 */
@Slf4j
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService, ApplicationListener<RegisterEvent> {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onApplicationEvent(RegisterEvent event) {
        log.info("用户注册时，组织服务进行事件处理");
        RegisterEventEntity entity = (RegisterEventEntity) event.getSource();
        String organizationName = entity.getOrganizationName();
        // 校验组织是否存在
        Organization organization = query().eq(COL_ORGANIZATION_NAME, organizationName)
                .one();
        if (Objects.isNull(organization)) {
            // 新增组织
            organization = new Organization();
            organization.setOrganizationName(organizationName)
                    .setOrganizationId(IdWorker.getIdStr());
            save(organization);
        }
        entity.setOrganizationId(organization.getOrganizationId());
    }
}
