package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(requests -> requests
                .requestMatchers(
                		"/pipboy/login",
                        "/login",
                        "/css/**",
                        "/js/**",
                        "/images/**"
                        , "/WEB-INF/views/login.jsp"
                ).permitAll()
                .requestMatchers("/tickets/new").hasAnyRole("DWELLER", "SCIENTIST", "SECURITY", "ADMIN")
                .requestMatchers("/tickets", "/tickets/**").authenticated()
                .requestMatchers("/dwellers/**").hasAnyRole("ADMIN", "SECURITY")
                .requestMatchers("/inventory/**").hasAnyRole("ADMIN", "SECURITY", "SCIENTIST")
                .requestMatchers("/radio/**", "/index").hasAnyRole("ADMIN", "SECURITY", "SCIENTIST", "DWELLER")
                .requestMatchers("/alerts/**").hasRole("SECURITY")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/index")
            )
            .exceptionHandling(handling -> handling
                .accessDeniedPage("/WEB-INF/views/access_denied.jsp")
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}