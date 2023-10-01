package com.sehbeomschool.nova.global.filter;

import static com.sehbeomschool.nova.global.error.constant.ExceptionMessage.NOT_VALID_TOKEN;

import com.sehbeomschool.nova.global.error.exception.NotFoundException;
import com.sehbeomschool.nova.global.util.JwtUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException, NotFoundException {

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token = null;
        try {
            if (authorization == null) {
                throw new NotFoundException(NOT_VALID_TOKEN.getMessage());
            }

            if (!authorization.startsWith("Bearer ")) {
                throw new NotFoundException(NOT_VALID_TOKEN.getMessage());
            }

            token = authorization.substring(7);

            if (!jwtUtil.isValidToken(token)) {
                throw new NotFoundException(NOT_VALID_TOKEN.getMessage());
            }
        } catch (NotFoundException e) {
            filterChain.doFilter(request, response);
            return;
        }

        Long userId = jwtUtil.getUserId(token);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userId, null, List.of(new SimpleGrantedAuthority("USER")));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
