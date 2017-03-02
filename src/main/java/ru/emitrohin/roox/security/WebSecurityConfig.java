package ru.emitrohin.roox.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


/**
 * Author: E_Mitrohin
 * Date:   02.03.2017.
 */


public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new BearerTokenAuthenticationFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated()
                .and().anonymous().disable();


    }

}
