package com.example.ComicScout.tutorial2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth)->{
                    //auth.requestMatchers("/").permitAll();
                    auth.anyRequest().authenticated();
                })
                //.oauth2Login(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

                return http.build();


    }
}
