package com.sehbeomschool.nova.global.config.security;

import com.sehbeomschool.nova.global.filter.JwtFilter;
import com.sehbeomschool.nova.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .httpBasic().disable()
            .csrf().disable()
            .cors().and()
            .authorizeRequests()
            .anyRequest().permitAll()
//            .antMatchers("/api/user/oauth/kakao").permitAll()
//            .antMatchers("/api/user").authenticated()
            .and()
            .addFilterBefore(new JwtFilter(jwtUtil),
                UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}
