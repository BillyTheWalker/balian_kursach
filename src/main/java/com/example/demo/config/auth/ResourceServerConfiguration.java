package com.example.demo.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import static com.example.demo.config.auth.AuthorizationServerConfiguration.RESOURCE_ID;


@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter
{

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.resourceId(RESOURCE_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
              .authorizeRequests()
              .antMatchers(HttpMethod.OPTIONS).permitAll()
              .antMatchers("/").permitAll()
              .antMatchers("/css/**").permitAll()
              .antMatchers("/js/**").permitAll()
              .antMatchers("/img/**").permitAll()
              .antMatchers("/oauth/token").permitAll()

              .antMatchers(HttpMethod.POST, "/professor/**").access("hasAuthority('ADMIN')")
              .antMatchers(HttpMethod.POST, "/work/**").access("hasAnyAuthority('ADMIN','PROFESSOR')")

              .antMatchers(HttpMethod.GET, "/professor/**","/work/**","/load/**","/user/**").access("hasAnyAuthority('ADMIN','PROFESSOR','CHASTYNA')")

              .antMatchers(HttpMethod.DELETE).access("hasAuthority('ADMIN')")

              .antMatchers(HttpMethod.GET, "/**").permitAll()

              .anyRequest().permitAll();
    }
}
