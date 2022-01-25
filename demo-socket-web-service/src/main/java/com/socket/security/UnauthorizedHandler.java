package com.socket.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.socket.payload.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(UnauthorizedHandler.class);
    @Autowired
    private Response response;
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.core.AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String,Object> map = new HashMap<>();
        map.put("status", 401);
        map.put("message", "UNAUTHORIZED");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(map);
        httpServletResponse.getOutputStream().println(json);
    }
}
