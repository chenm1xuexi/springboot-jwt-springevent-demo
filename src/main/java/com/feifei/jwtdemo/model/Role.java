package com.feifei.jwtdemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author shixiongfei
 * @date 2020-03-16
 * @since 
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_role")
public class Role {
    /**
     * 资源主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private String roleId;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    public static final String COL_ID = "id";

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_ROLE_NAME = "role_name";
}