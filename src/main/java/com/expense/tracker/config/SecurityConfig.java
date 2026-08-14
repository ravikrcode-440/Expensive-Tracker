package com.expense.tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// 1. Password ko encrypt karne ke liye - BCrypt use hoga
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	// 2. Kis page pe login chahiye, kis pe nahi - ye yaha set hota hai
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll() // Ye
																														// pages
																														// bina
																														// login
																														// khulenge
				.anyRequest().authenticated() // Baaki sab pages ke liye login jaruri
		).formLogin(form -> form.loginPage("/login") // Hamara custom login page ka URL
				.loginProcessingUrl("/login") // Form submit hone pe kaha jaye
				.defaultSuccessUrl("/dashboard", true) // Login ke baad kaha bhejna
				.permitAll())
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll());

		return http.build();
	}
}