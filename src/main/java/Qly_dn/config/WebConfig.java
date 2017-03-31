package Qly_dn.config;

import Qly_dn.interceptor.TokenAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by nhkha on 25/03/2017.
 */
@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter {
    @Autowired
    TokenAuthenticationInterceptor tokenAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenAuthenticationInterceptor);
    }
}
