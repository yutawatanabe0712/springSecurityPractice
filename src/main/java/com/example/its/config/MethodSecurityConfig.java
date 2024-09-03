package com.example.its.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//@PreAuthorize(UserService.java)を有効にする設定
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig {
}
