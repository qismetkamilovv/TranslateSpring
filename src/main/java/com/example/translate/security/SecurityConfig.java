package com.example.translate.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    // authentication
    public UserDetailsService userDetailsService() {
        // UserDetails admin = User.withUsername("qismet")
        //         .password(encoder.encode("qismet123"))
        //         .roles("ADMIN")
        //         .build();
        // UserDetails user = User.withUsername("ismet")
        //         .password(encoder.encode("ismet123"))
        //         .roles("USER")
        //         .build();
        // return new InMemoryUserDetailsManager(admin, user);
        return new UserInfoUserDetailsService() ;
      
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // http.authorizeHttpRequests((requests) -> requests
        //         .requestMatchers("/all", "/get/{sourceText}","/new").permitAll()
        //         .anyRequest().authenticated())
        //         .logout((logout) -> logout.permitAll());

        // return http.build();

        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/all", "/get/{sourceText}","/new").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/**")
                .authenticated().and().formLogin().and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider ;

    }
}
