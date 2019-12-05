package com.server.blog.system.interceptor;


import com.server.blog.common.annotation.LoginRequired;
import com.server.blog.common.base.domain.vo.UserSessionVO;
import com.server.blog.common.constant.Constants;
import com.server.blog.common.constant.ErrorConstants;
import com.server.blog.common.util.ExceptionUtil;
import com.server.blog.common.util.SessionUtil;
import com.server.blog.system.enums.RoleEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Configuration
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        //从HTTP请求出取出token
        String token = httpServletRequest.getHeader(Constants.AUTHENTICATION);

        //如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有LoginRequired注释，没有有则跳过认证
        if (!method.isAnnotationPresent(LoginRequired.class)) {
            return true;
        }

        LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
        if (loginRequired.required()) {
            // 执行认证
            if (token == null) {
                ExceptionUtil.rollback("无token，请重新登录", ErrorConstants.INVALID_TOKEN);
            }

            RoleEnum role = loginRequired.role();
            if (role == RoleEnum.USER) {
                return true;
            }

            if (role == RoleEnum.ADMIN) {
                UserSessionVO userSessionInfo = SessionUtil.getUserSessionInfo();
                if (role != RoleEnum.getEnumTypeMap().get(userSessionInfo.getRoleId())) {
                    ExceptionUtil.rollback("", ErrorConstants.ACCESS_NO_PRIVILEGE);
                }
            }

            return true;
        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}
