package com.zjw.gmbackend.interceptor;

import com.zjw.gmbackend.util.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        Long userId = UserContext.getUserId();

        // 未登录 或 非管理员
        if (userId == null || userId != 1L) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(
                    "{\"code\":403,\"msg\":\"无管理员权限\"}"
            );
            return false;
        }

        return true;
    }
}

