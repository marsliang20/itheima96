package com.itheima.interceptor;

import com.itheima.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断是否登录, 未登录就不允许访问
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是未登录，则不允许访问
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //未登录
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return false;
        }

        return true;
    }
}
