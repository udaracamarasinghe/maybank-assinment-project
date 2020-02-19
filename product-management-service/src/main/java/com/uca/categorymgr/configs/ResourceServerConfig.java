package com.uca.categorymgr.configs;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${token.endpoint.url}")
	private String tokenEndPointURL;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(filterChainExceptionHandlerFilter(), SecurityContextPersistenceFilter.class)
				.authorizeRequests().antMatchers("/**").authenticated().and().cors().disable();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		RemoteTokenServices tokenServices = new RemoteTokenServices();
		tokenServices.setClientId("client-credintials");
		tokenServices.setClientSecret("test_secret");
		tokenServices.setCheckTokenEndpointUrl(tokenEndPointURL + "/oauth/check_token");

		resources.tokenServices(tokenServices);
	}

	@Bean
	public Filter filterChainExceptionHandlerFilter() {
		return new FilterChainExceptionHandlerFilter();
	}

}
