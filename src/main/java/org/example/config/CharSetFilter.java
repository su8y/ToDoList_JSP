package org.example.config;

import javax.servlet.*;
import java.io.IOException;

public class CharSetFilter implements Filter {
    FilterConfig config;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(config.getInitParameter("encoding"));
        response.setCharacterEncoding(config.getInitParameter("encoding"));
        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
