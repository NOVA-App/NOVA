package com.sehbeomschool.nova.global.filter;

import static com.sehbeomschool.nova.global.error.constant.ExceptionMessage.NOT_VALID_TOKEN;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import com.sehbeomschool.nova.global.error.exception.NotFoundException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (NotFoundException e) {
            response.setStatus(400);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            String json = new ObjectMapper().writeValueAsString(
                ResponseDto.create(NOT_VALID_TOKEN.getMessage()));
            response.getWriter().write(json);
        }
    }
}
