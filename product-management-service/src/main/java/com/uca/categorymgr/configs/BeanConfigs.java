package com.uca.categorymgr.configs;

import java.time.Duration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Configuration
public class BeanConfigs {

	@Value("${rest.timeout.connection}")
	private Integer connectionTimeout;

	@Value("${rest.timeout.read}")
	private Integer readTimeOut;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		builder.setConnectTimeout(Duration.ofMillis(connectionTimeout));
		builder.setReadTimeout(Duration.ofMillis(readTimeOut));

		return builder.build();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
