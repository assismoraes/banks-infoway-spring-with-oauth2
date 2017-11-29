package com.assismoraes.bank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServeConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/api/bank/**").hasRole("ADMIN")
			.antMatchers("/api/branch/**").hasRole("ADMIN")
			.antMatchers("/api/account/**").hasRole("ADMIN")
			.antMatchers("/api/transaction/**").hasRole("USER")
			.anyRequest()
			.authenticated();
	}
	
}
