package ru.emitrohin.roox.repository;

import org.springframework.data.repository.Repository;
import ru.emitrohin.roox.model.Customer;

/**
 * Customer repository interface. Uses spring data {@code Repository} interface to execute queries based on method
 * naming convention
 *
 * @author Evgeniy Mitrokhin
 */
public interface CustomerRepository extends Repository<Customer, Integer> {

    /**
     * Select customer from customer table by customer_id
     *
     * @param customerId
     * @return Customer from database
     */
    Customer findOne(int customerId);

    /**
     * Select customer from customer table by login
     *
     * @param login
     * @return Customer from database
     */
    Customer findByLogin(String login);
}
