package com.zjw.gmbackend.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjw.gmbackend.pojo.Result;
import com.zjw.gmbackend.util.JwtUtil;
import com.zjw.gmbackend.util.UserContext;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

public class AuthInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("====== AuthInterceptor ======");
        System.out.println("URI = " + request.getRequestURI());
        System.out.println("Method = " + request.getMethod());
        System.out.println("Authorization = [" + request.getHeader("Authorization") + "]");

        // 1) 放行 OPTIONS（跨域预检）
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // 2) 从 Header 取 token
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.isBlank()) {
            writeUnauthorized(response, "未登录：缺少 Authorization");
            return false;
        }

        // 3) Bearer token 格式校验
        if (!authHeader.startsWith("Bearer ")) {
            writeUnauthorized(response, "未登录：Authorization 格式应为 Bearer token");
            return false;
        }

        String token = authHeader.substring(7).trim();
        if (token.isEmpty()) {
            writeUnauthorized(response, "未登录：token 为空");
            return false;
        }

        // 4) 解析 JWT
        try {
            Long userId = JwtUtil.getUserId(token);
            if (userId == null) {
                writeUnauthorized(response, "未登录：token 无效");
                return false;
            }

            // 5) 放入 UserContext
            UserContext.setUserId(userId);
//            System.out.println(userId);
            return true;

        } catch (JwtException e) {
            e.printStackTrace();
            // token 过期、签名不对、格式不对都会进来
            writeUnauthorized(response, "未登录：token 无效或已过期");
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 防止线程复用导致串号
        UserContext.clear();
    }

    private void writeUnauthorized(HttpServletResponse response, String msg) throws Exception {
        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");

        Result r = Result.error(msg);
        String json = objectMapper.writeValueAsString(r);

        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
    }
}