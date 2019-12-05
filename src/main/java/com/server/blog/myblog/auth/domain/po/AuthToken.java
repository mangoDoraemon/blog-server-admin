package com.server.blog.myblog.auth.domain.po;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class AuthToken implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 创建人
     */
    private Long userId;




}
