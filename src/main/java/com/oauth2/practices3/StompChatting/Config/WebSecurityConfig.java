package com.oauth2.practices3.StompChatting.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable) // 기본적으로 ON인 csrf 취약점 보안을 해제한다.
                .headers((header) -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // SockJS는 기본적으로 HTML ifram 요소를 통한 전송을 허용하지 않도록 설정되는데, 해당 설정을 해제한다.// 권한 없이 페이지 접근 시 로그인 페이지로 이동 시킴
                .authorizeHttpRequests((request) -> request.anyRequest().permitAll()); // 리소스에 대한 모든 접근 다 허용


        return http.build();
    }
}