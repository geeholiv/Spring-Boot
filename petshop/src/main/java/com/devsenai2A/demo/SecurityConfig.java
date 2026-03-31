package com.devsenai2A.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableWebSecurity 
public class SecurityConfig { 
@Bean 
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
http 
.csrf(csrf -> csrf.disable()) 
.cors(cors -> {})
.authorizeHttpRequests(auth -> auth 
.requestMatchers("/api/categorias/**").permitAll() 
.anyRequest().authenticated() 

); 
return http.build(); 
}
@Bean 
public BCryptPasswordEncoder passwordEncoder() { 
return new BCryptPasswordEncoder(); 
} 
}
