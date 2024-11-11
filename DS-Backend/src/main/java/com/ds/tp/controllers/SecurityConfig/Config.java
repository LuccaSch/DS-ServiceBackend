package com.ds.tp.controllers.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import com.ds.tp.services.BedelServiceUserDetails;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class Config {
       @Autowired
       BedelServiceUserDetails bedelServiceUserDetails;
       
       PasswordEncoder passwordEncoder;
    String[] resources = new String[]{
        "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
};
@Bean
public PasswordEncoder passwordEncoder() {
    return new PasswordEnconderTest();
}

   
@Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
    auth.userDetailsService(bedelServiceUserDetails).passwordEncoder(passwordEncoder());
    return auth.build();
}

@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/public/**").permitAll()
                .requestMatchers(resources).permitAll()
                .requestMatchers("/registrarBedel.html").permitAll()
                        .requestMatchers("/v1/home").authenticated()
                        .requestMatchers("/v1/admin").hasAuthority("ADMIN").anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }












}
