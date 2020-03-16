package com.feifei.jwtdemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shixiongfei
 * @date 2020-03-16
 * @since
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_user")
public class User {
    /**
     * 资源主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 用户名称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码 md5加密
     */
    @TableField(value = "password")
    private String password;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private String roleId;

    /**
     * 组织id,以此区分租户
     */
    @TableField(value = "organization_id")
    private String organizationId;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_ORGANIZATION_ID = "organization_id";
}