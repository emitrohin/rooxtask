package ru.emitrohin.roox.security.t;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.emitrohin.roox.service.CustomerService;

/**
 * Author: E_Mitrohin
 * Date:   02.03.2017.
 */

@EnableWebSecurity
public class TokenAuthenticationConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    private CustomerService customerService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .and().antMatcher("/**").addFilterBefore(new AuthenticationFilter(customerService), BasicAuthenticationFilter.class)
                .authenticationProvider(tokenAuthenticationProvider)
                .authorizeRequests().antMatchers("/**").authenticated()
                .and().authorizeRequests()
                .anyRequest().denyAll();
    }
}
