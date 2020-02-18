package com.example.demo.configs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
public class PasswordEncoderConfig {

	@Bean
	public PasswordEncoder delegatingPasswordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());

		DelegatingPasswordEncoder passworEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
		passworEncoder.setDefaultPasswordEncoderForMatches(encoders.get("bcrypt"));

		return passworEncoder;
	}

}
