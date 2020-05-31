package com.example.demo.config.auth;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import java.util.concurrent.TimeUnit;


@Configuration
@ComponentScan(basePackages = {"com.example.*"})
public class ApplicationWebMvcConfig extends WebMvcConfigurerAdapter
{
    String rootPath = System.getProperty("catalina.home");
    String[] PATH = {
            "classpath:/resources/",
            "file:/" + rootPath + "/resources/"
    };

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations(PATH)
                .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
                .resourceChain(false)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }

}
