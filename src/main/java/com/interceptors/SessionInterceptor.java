package com.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 判断是否登录的拦截器
 * @author 码上猿梦
 *  http://www.cnblogs.com/daimajun/
 */
public class SessionInterceptor  implements HandlerInterceptor {

    
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        
    }

    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handel) throws Exception {
       /* HttpSession session = req.getSession();
        // 从session当中获取特定的数据
        Object obj = session.getAttribute("name");
        if (obj == null) {
            // 未登录，重定向到登录页面
            res.sendRedirect(req.getContextPath()+"/login.html");
            return false;
        }
        // 已登录，继续向后调用
*/        return true;
    }
}