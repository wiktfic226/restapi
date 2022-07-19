package pl.fis.restapi.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationStudentFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if(headerName.equals("role") && (request.getHeader(headerName).equals("STUDENT_ROLE")
                    || request.getHeader(headerName).equals("TEACHER_ROLE")))
                filterChain.doFilter(servletRequest, servletResponse);
            else {
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                Map<String, Object> errorDetails = new HashMap<>();
                ObjectMapper mapper = new ObjectMapper();
                errorDetails.put("errorMessage", "User unauthorized!");
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                mapper.writeValue(httpServletResponse.getWriter(), errorDetails);
                return;
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
