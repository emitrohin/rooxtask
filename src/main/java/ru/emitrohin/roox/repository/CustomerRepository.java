package ru.emitrohin.roox.repository;

import org.springframework.data.repository.Repository;
import ru.emitrohin.roox.model.Customer;

import java.util.List;

/**
 * Created by emitrokhin on 28.02.2017.
 */
public interface CustomerRepository extends Repository<Customer, Integer> {

    Customer findOne(Integer id);

    List<Customer> findAll(int id);

}
