package sjht.erp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sjht.erp.interceptor.Interceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new Interceptor())
                .addPathPatterns("/test")
                .excludePathPatterns("/css/**", "/fonts/**", "/plugin/**", "/scripts/**");


        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
