package com.zjw.gmbackend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 明文 → 加密
    public static String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    // 校验：明文 vs 数据库加密密码
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
