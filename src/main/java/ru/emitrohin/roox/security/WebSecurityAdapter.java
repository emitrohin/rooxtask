package ru.emitrohin.roox.security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.emitrohin.roox.model.Customer;
import ru.emitrohin.roox.repository.CustomerRepository;

/**
 * Author: mitrokhin
 * Date:   22.01.17.
 */


public class WebSecurityAdapter extends GlobalAuthenticationConfigurerAdapter {

    private CustomerRepository customerRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return login -> {
            Customer customer = customerRepository.findByLogin(login);
            if(customer != null) {
                return new AuthorizedCustomer(customer);
            } else {
                throw new UsernameNotFoundException("Could not find the user '" + login + "'");
            }
        };
    }

}
