package com.example.oauth2oidclearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrf) -> csrf.disable()) //CSRF (Cross-Site Request Forgery) 보호 기능을 끔
                .formLogin((login) -> login.disable()) // Spring Security 기본 로그인 폼을 비활성화함
                .httpBasic((basic) -> basic.disable()) // 브라우저 팝업으로 로그인 요청하는 기본 인증 방식 비활성화
                .oauth2Login(Customizer.withDefaults()) // OAuth2 로그인 설정을 기본값으로 활성화합니다
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/**").permitAll() // 모든 경로에 대해 모두 접근 허용
                        .anyRequest().authenticated() // 그 외 요청 인증 요구
                );

        return http.build();
    }
}
