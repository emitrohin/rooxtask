package ru.emitrohin.roox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.emitrohin.roox.model.Customer;
import ru.emitrohin.roox.repository.CustomerRepository;
import ru.emitrohin.roox.util.exception.ExceptionUtil;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer get(int authorizedCustomerId) {
        Customer customer = customerRepository.findOne(authorizedCustomerId);
        return ExceptionUtil.checkNotFound(customer, "customer with id " + authorizedCustomerId);
    }
}
