package com.assismoraes.bank;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.assismoraes.bank.models.Role;
import com.assismoraes.bank.models.Usr;
import com.assismoraes.bank.repos.UserRepository;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}
	
	@Autowired
	public void authenticationManage(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
		if(repo.count() == 0)
			repo.save(new Usr("user", "user", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
		builder.userDetailsService((UserDetailsService) param -> new CustomUserDetails(repo.findByUsername(param)));
	}
}
