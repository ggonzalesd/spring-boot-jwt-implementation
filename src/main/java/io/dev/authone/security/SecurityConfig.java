package io.dev.authone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import io.dev.authone.jwt.JwtAuthorizationFilter;

@Configuration
public class SecurityConfig {

  @Autowired
  private JwtAuthorizationFilter jwtAuthorizationFilter;

  @Bean
  SecurityFilterChain securityFilterChain(
    HttpSecurity httpSecurity
  ) throws Exception {
    return httpSecurity
      .csrf(csrf -> csrf.disable())
      .formLogin(form -> form.disable())
      .httpBasic(http -> http.disable())
      .cors(cors -> cors.configurationSource(request -> {
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        return config;
      }))
      .authorizeHttpRequests(auth -> {
        auth.requestMatchers("/auth/login").permitAll();
        auth.requestMatchers("/auth/register").permitAll();
        auth.anyRequest().authenticated();
      })
      .sessionManagement(session -> 
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
      .build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
