/*
 */
package com.shops.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Abdessamad
 */
@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

    /**
     * a method that create a Bean of the userDetailsService
     * @return 
     */
    @Bean
    public UserDetailsService mongoUserDetails() {
        return new MyUserDetailsService();
    }
    
    /**
     * Setting the userDetailsService
     * @param auth
     * @throws Exception 
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth.userDetailsService(userDetailsService);
    }
    
    /**
     * Defining the routes permissions
     * @param http
     * @throws Exception 
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/shops/allNearby/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
    
    

}
