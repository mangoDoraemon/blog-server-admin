package com.server.blog.myblog.auth.domain.po;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author byteblogs
 * @since 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    private Long id;
    /**
     * 用户名
     */
    private String socialId;

    /**
     * 别名
     */
    private String name;

    /**
     * 角色主键 1 普通用户 2 admin
     */
    private Long roleId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;

    /**
     * 密码
     */
    private String password;

    /**
     * 个人介绍
     */
    private String introduction;

    /**
     * 邮箱
     */
    private String email;

    /**
     * github个人主页
     */
    private String htmlUrl;

    private String qq;

    private String csdn;

    private String weibo;

    private String twitter;

    private String facebook;
    






}
