package com.example.seckillservice.config;

import com.example.seckillservice.aop.MyAnntaHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    final String[] notLoginInterceptPaths = {"/do_login/**"};
    @Autowired
    private MyAnntaHandler myAnntaHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(myAnntaHandler).addPathPatterns("/**").excludePathPatterns(notLoginInterceptPaths);
//        registry.addInterceptor(interceptor);
    }


}
