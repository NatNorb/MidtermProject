package com.ironhack.MidtermProject.security;

import com.ironhack.MidtermProject.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/admin/**").hasAnyRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/admin/**").hasAnyRole("ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/admin/**").hasAnyRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/account-holder/**").hasAnyRole("ACCOUNT-HOLDER")
                .mvcMatchers(HttpMethod.PUT, "/account-holder/**").hasAnyRole("ACCOUNT-HOLDER")
                .mvcMatchers(HttpMethod.PUT, "/third-party/**").hasAnyRole("THIRD-PARTY");

//                .mvcMatchers(HttpMethod.GET, "/hello-world").authenticated()
//                .mvcMatchers(HttpMethod.GET, "/courses").authenticated()
//                .mvcMatchers(HttpMethod.GET, "/products").authenticated()
//                .mvcMatchers(HttpMethod.GET, "/products/**").hasAnyRole("ADMIN","USER")
//                .mvcMatchers(HttpMethod.POST, "/products").hasAnyRole("ADMIN","TECHNICIAN")
//                .anyRequest().permitAll();
    }
}
