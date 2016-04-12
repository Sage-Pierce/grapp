package com.wisegas.common.webserver.servlet.filter;

import javax.servlet.*;
import java.io.IOException;

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