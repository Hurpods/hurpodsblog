package com.hurpods.ssm.blog.interceptor;

import com.hurpods.ssm.blog.models.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserPermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean flag = false;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null) {
            flag = true;
        }
        if (httpServletRequest.getRequestURL().indexOf("admin") != -1) {
            assert user != null;
            flag= user.getIsAdmin()==1;
        }
        if(!flag){
            httpServletResponse.sendError(500,"访问禁止！您没有权限访问本页面");
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
