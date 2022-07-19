package pl.fis.restapi.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public abstract class AuthorizationFilter {

    protected boolean checkStudentAuthorizationHeaders(HttpServletRequest httpServletRequest) {
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (headerName.equals("role") && (httpServletRequest.getHeader(headerName).equals("STUDENT_ROLE")
                    || httpServletRequest.getHeader(headerName).equals("TEACHER_ROLE")))
                return true;
        }
        return false;
    }

    protected boolean checkTeacherAuthorizationHeaders(HttpServletRequest httpServletRequest) {
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (headerName.equals("role") && httpServletRequest.getHeader(headerName).equals("TEACHER_ROLE"))
                return true;
        }
        return false;
    }
    protected void returnErrorMessage(HttpServletResponse httpServletResponse) throws IOException {
        Map<String, Object> errorDetails = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        errorDetails.put("errorMessage", "User unauthorized!");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(httpServletResponse.getWriter(), errorDetails);
    }
}
