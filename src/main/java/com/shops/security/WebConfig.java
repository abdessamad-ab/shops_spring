/*
 */
package com.shops.security;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author Abdessamad
 */
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

    /**
     * a method that create a Bean of the userDetailsService
     *
     * @return
     */
    @Bean
    public UserDetailsService mongoUserDetails() {
        return new MyUserDetailsService();
    }

    /**
     * Setting the userDetailsService
     *
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
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic();
        /**
         * // by default http.cors() uses a Bean by the name of corsConfigurationSource
         */
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers("/authentication", "/register", "/shops/allNearby/**").permitAll()
                .anyRequest().authenticated();
    }

    /**
     * Configuration of the Cross-origin resource sharing
     * Allowing the Request methods to be called from another Origin
     * @return 
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
