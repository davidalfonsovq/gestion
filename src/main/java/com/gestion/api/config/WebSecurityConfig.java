package com.gestion.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The Class WebSecurityConfig.
 */
@Configuration
public class WebSecurityConfig { 

	/** The jwt request filter. */
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	/** The jwt authentication entry point. */
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	/**
	 * Authentication manager.
	 *
	 * @param authConfig the auth config
	 * @return the authentication manager
	 * @throws Exception the exception
	 */
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	/**
	 * Password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Filter chain.
	 *
	 * @param http the http
	 * @return the security filter chain
	 * @throws Exception the exception
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/authenticate").permitAll()
	            .requestMatchers("/h2-console/**").permitAll()
	            .requestMatchers("/swagger/**").permitAll()
	            .requestMatchers("/swagger-ui/**").permitAll()
	            .requestMatchers("/swagger-resources/**").permitAll()
	            .requestMatchers("/v3/api-docs/**").permitAll()
	            .anyRequest().authenticated());

	    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	}
}
