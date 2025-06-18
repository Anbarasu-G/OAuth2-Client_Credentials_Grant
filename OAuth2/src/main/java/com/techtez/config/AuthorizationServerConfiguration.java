package com.techtez.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthorizationServerConfiguration {

	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http
				.with(
						OAuth2AuthorizationServerConfigurer.authorizationServer(),
						Customizer.withDefaults())
				.build();

	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	/*
	 * RegisteredClientRepository
	 * registeredClientRepository(CustomRegisteredClientRepository
	 * customRegisteredClientRepository) {
	 * 
	 * return customRegisteredClientRepository; }
	 */
}
