package pl.fis.restapi.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Order(1)
@Component
public class TimeWatcherFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(TimeWatcherFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.nanoTime();
        filterChain.doFilter(servletRequest, servletResponse);
        long endTime = System.nanoTime();
        LOGGER.info(String.format("Execution of request took %d nanoseconds.", endTime - startTime));
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
