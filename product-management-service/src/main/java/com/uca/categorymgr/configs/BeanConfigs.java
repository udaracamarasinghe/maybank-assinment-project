package com.uca.categorymgr.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Configuration
public class BeanConfigs {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
}
