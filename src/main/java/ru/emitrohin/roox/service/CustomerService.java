package ru.emitrohin.roox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.emitrohin.roox.model.Customer;
import ru.emitrohin.roox.repository.CustomerRepository;
import ru.emitrohin.roox.security.AuthorizedCustomer;
import ru.emitrohin.roox.util.exception.ExceptionUtil;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */

@Service
public class CustomerService implements UserDetailsService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer get(int authorizedCustomerId) {
        Customer customer = customerRepository.findOne(authorizedCustomerId);
        return ExceptionUtil.checkNotFound(customer, "customer with id " + authorizedCustomerId);
    }

    @Override
    public AuthorizedCustomer loadUserByUsername(String login) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByLogin(login.toLowerCase());
        if (customer == null) {
            throw new UsernameNotFoundException("User " + login + " is not found");
        }
        return new AuthorizedCustomer(customer);
    }

    public AuthorizedCustomer loadUserById(int id) throws UsernameNotFoundException {
        Customer customer = get(id);
        if (customer == null) {
            throw new UsernameNotFoundException("User " + id + " is not found");
        }
        return new AuthorizedCustomer(customer);
    }
}
