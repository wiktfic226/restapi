package pl.fis.restapi.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationStudentFilter extends AuthorizationFilter implements Filter {
    private final Logger LOGGER = LoggerFactory.getLogger(TimeWatcherFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (checkStudentAuthorizationHeaders((HttpServletRequest) servletRequest))
            filterChain.doFilter(servletRequest, servletResponse);
        else
            returnErrorMessage((HttpServletResponse) servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
