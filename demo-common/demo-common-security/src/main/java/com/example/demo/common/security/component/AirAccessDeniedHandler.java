package com.example.demo.common.security.component;

import com.example.demo.common.core.exception.AirDeniedException;
import com.example.demo.common.core.response.AirResult;
import com.example.demo.common.security.constant.CommonConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @Author oyg
 * @Date 2019/5/13/21:03
 */
@Component
public class AirAccessDeniedHandler extends OAuth2AccessDeniedHandler {

    private final static Logger log = LoggerFactory.getLogger(AirAccessDeniedHandler.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {
        log.info("授权失败，禁止访问 {}", request.getRequestURI());
        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(CommonConstants.CONTENT_TYPE);
        AirResult<String> result = new AirResult<>();
        result.setMsg("授权失败，禁止访问");
        result.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(result));
    }
}
