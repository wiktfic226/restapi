package pl.fis.restapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.fis.restapi.interceptor.AuthorizeInterceptor;

@Configuration
public class AuthorizationInterceptorConfig implements WebMvcConfigurer {
    private final AuthorizeInterceptor authorizeInterceptor;

    public AuthorizationInterceptorConfig(AuthorizeInterceptor authorizeInterceptor) {
        this.authorizeInterceptor = authorizeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizeInterceptor)
                .addPathPatterns("/api/student", "/api/teacher");
    }
}
