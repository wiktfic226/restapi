package pl.fis.restapi.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.fis.restapi.filter.AuthorizationStudentFilter;

@Configuration
public class AuthorizationStudentFilterConfig {
    @Bean
    public FilterRegistrationBean<AuthorizationStudentFilter> filter() {
        FilterRegistrationBean<AuthorizationStudentFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AuthorizationStudentFilter());
        bean.addUrlPatterns("/api/student/*");
        bean.setName("AuthorizationStudentFilter");
        bean.setOrder(2);
        return bean;
    }
}
