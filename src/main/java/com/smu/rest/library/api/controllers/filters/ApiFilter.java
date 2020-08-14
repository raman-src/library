package com.smu.rest.library.api.controllers.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if(req.getHeader("apiKey") != null ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        res.setStatus(400);
        res.getWriter().println("No apiKey provided");
    }

    @Override
    public void destroy() {

    }
}
