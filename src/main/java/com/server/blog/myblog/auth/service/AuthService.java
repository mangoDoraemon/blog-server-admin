package com.server.blog.myblog.auth.service;

import com.server.blog.common.base.domain.Result;
import com.server.blog.myblog.auth.domain.vo.AuthUserVO;

public interface AuthService {

    /**
     * 注册用户信息
     * @param authUserVO
     * @return
     */
    Result saveUser(AuthUserVO authUserVO);
}
