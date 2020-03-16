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
@Accessors(chain = true
)
@TableName(value = "t_organization")
public class Organization {
    /**
     * 资源主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 组织id
     */
    @TableField(value = "organization_id")
    private String organizationId;

    /**
     * 组织名称
     */
    @TableField(value = "organization_name")
    private String organizationName;

    public static final String COL_ID = "id";

    public static final String COL_ORGANIZATION_ID = "organization_id";

    public static final String COL_ORGANIZATION_NAME = "organization_name";
}