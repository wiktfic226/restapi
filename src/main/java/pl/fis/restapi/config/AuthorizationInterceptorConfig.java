package pl.fis.restapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.fis.restapi.interceptor.AuthorizeInterceptor;

@Configuration
public class AuthorizationInterceptorConfig extends WebMvcConfigurerAdapter {
    private final AuthorizeInterceptor authorizeInterceptor;

    public AuthorizationInterceptorConfig(AuthorizeInterceptor authorizeInterceptor) {
        this.authorizeInterceptor = authorizeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizeInterceptor);
    }
}
