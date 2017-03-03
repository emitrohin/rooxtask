package ru.emitrohin.roox.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.emitrohin.roox.service.CustomerService;

/**
 * Class for custom token authentication configuration.
 * Configures access to all resources, session, csrf
 *
 * @author Evgeniy Mitrokhin
 */
@EnableWebSecurity
public class TokenAuthenticationConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationProvider tokenAuthenticationProvider;

    private final CustomerService customerService;

    @Autowired
    public TokenAuthenticationConfiguration(TokenAuthenticationProvider tokenAuthenticationProvider, CustomerService customerService) {
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
        this.customerService = customerService;
    }

    /**
     * This method sets SessionCreationPolicy to stateless, disables csrf, adds TokenAuthenticationFilter before
     * BasicAuthenticationFilter and applies it to all resources
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf().disable();

        http.authorizeRequests()
                .and().antMatcher("/**").addFilterBefore(new TokenAuthenticationFilter(customerService), BasicAuthenticationFilter.class)
                .authenticationProvider(tokenAuthenticationProvider)
                .authorizeRequests().antMatchers("/**").authenticated()
                .and().authorizeRequests()
                .anyRequest().denyAll();


    }
}
