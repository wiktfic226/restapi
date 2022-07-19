package pl.fis.restapi.service;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public boolean isAuthorized(String requestURI, String roleHeader) {
        if(requestURI.startsWith("/api/student") && roleHeader!= null && (roleHeader.equals("TEACHER_ROLE") || roleHeader.equals("STUDENT_ROLE")))
            return true;
        if(requestURI.startsWith("/api/teacher") && roleHeader!= null && roleHeader.equals("TEACHER_ROLE"))
            return true;
        return false;
    }
}
