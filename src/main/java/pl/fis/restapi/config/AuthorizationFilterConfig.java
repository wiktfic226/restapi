package pl.fis.restapi.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.fis.restapi.filter.AuthorizationStudentFilter;
import pl.fis.restapi.filter.AuthorizationTeacherFilter;

@Configuration
public class AuthorizationFilterConfig {
    @Bean
    public FilterRegistrationBean<AuthorizationStudentFilter> studentFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuthorizationStudentFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AuthorizationStudentFilter());
        bean.addUrlPatterns("/api/student/*");
        bean.setName("AuthorizationStudentFilter");
        bean.setOrder(2);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationTeacherFilter> teacherFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuthorizationTeacherFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AuthorizationTeacherFilter());
        bean.addUrlPatterns("/api/teacher/*");
        bean.setName("AuthorizationTeacherFilter");
        bean.setOrder(3);
        return bean;
    }
}
