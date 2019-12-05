package com.server.blog.myblog.auth.controller;


import com.server.blog.common.base.domain.Result;
import com.server.blog.myblog.auth.domain.vo.AuthUserVO;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthUserController {


    public Result userLogin(){
       return null;
    }

    /**
     * 注册用户
     * @param authUserVO
     * @return
     */
    @PutMapping("/user/save")
    public Result saveUser(@RequestBody AuthUserVO authUserVO){
        return null;
    }

}
