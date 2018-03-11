package org.codegas.commons.webservices.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class StaticContentFilter implements Filter {

    private RequestDispatcher requestDispatcher;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        requestDispatcher = filterConfig.getServletContext().getNamedDispatcher("spring");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        requestDispatcher.forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}