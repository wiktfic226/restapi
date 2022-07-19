package pl.fis.restapi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationTeacherFilter extends AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (checkTeacherAuthorizationHeaders((HttpServletRequest) servletRequest))
            filterChain.doFilter(servletRequest, servletResponse);
        else
            returnErrorMessage((HttpServletResponse) servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
