package com.example.demo.config;

import com.example.demo.persistense.models.enums.UserRole;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{

//	public static final String RESOURCE_ID = "resource_id";


//	@Bean
//	@ConfigurationProperties(prefix = "security.oauth2.custom.client")
//	public ClientCredentialsResourceDetails clientCredentialsResourceDetails()
//	{
//		return new ClientCredentialsResourceDetails();
//	}

	@Bean
	@Primary
	public PasswordEncoder passwordEncoder()
	{
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources)
//	{
//		resources.resourceId(RESOURCE_ID).stateless(false);
//	}

	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http
				.csrf().disable()
				.headers().cacheControl().disable()
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers(HttpMethod.GET).authenticated()
            .antMatchers(HttpMethod.POST,"/work/**/**","/professor/**/**").hasAnyRole(UserRole.ADMIN.toString(), UserRole.PROFESSOR.toString())
            .antMatchers("/oauth/**").permitAll()
            .anyRequest().permitAll()
            .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}
