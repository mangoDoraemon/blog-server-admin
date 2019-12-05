package com.server.blog.myblog.auth.service.imp;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.server.blog.common.base.domain.Result;
import com.server.blog.common.constant.ErrorConstants;
import com.server.blog.common.util.ExceptionUtil;
import com.server.blog.myblog.auth.dao.AuthUserDao;
import com.server.blog.myblog.auth.domain.po.AuthUser;
import com.server.blog.myblog.auth.domain.vo.AuthUserVO;
import com.server.blog.myblog.auth.service.AuthService;

import com.server.blog.system.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
public class AuthServiceImp implements AuthService {


    @Autowired
    private AuthUserDao authUserDao;

    @Override
    public Result saveUser(AuthUserVO authUserVO) {
        log.debug("saveUser {}", authUserVO);
        if(authUserVO == null|| StringUtils.isBlank(authUserVO.getSocialId())){
            ExceptionUtil.rollback("参数异常", ErrorConstants.PARAM_INCORRECT);
        }

        AuthUser authUser = this.authUserDao.selectOne(new LambdaQueryWrapper<AuthUser>().eq(AuthUser::getSocialId, authUserVO.getSocialId()));
        if(authUser == null){
                authUser = new AuthUser();
                authUser.setSocialId(authUserVO.getSocialId());
            authUser.setAvatar(authUserVO.getAvatar());
            authUser.setName(authUserVO.getName());
            authUser.setRoleId(RoleEnum.USER.getRoleId());
            authUser.setPassword(SecureUtil.hmacMd5(RandomStringUtils.random(32)).digestHex(authUserVO.getSocialId()));
            authUser.setHtmlUrl(authUserVO.getHtmlUrl());
            authUser.setCreateTime(LocalDateTime.now());
            this.authUserDao.insert(authUser);
        }
        return null;
    }
}
