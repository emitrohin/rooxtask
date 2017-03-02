package ru.emitrohin.roox.repository;

import org.springframework.data.repository.Repository;
import ru.emitrohin.roox.model.Customer;

/**
 * Created by emitrokhin on 28.02.2017.
 */
public interface CustomerRepository extends Repository<Customer, Integer> {
    Customer findOne(int customerId);

    Customer findByLogin(String login);
}
