package ru.emitrohin.roox.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.emitrohin.roox.util.exception.NotFoundException;

import static ru.emitrohin.roox.testdata.CustomerTestData.MATCHER;
import static ru.emitrohin.roox.testdata.CustomerTestData.TEST_CUSTOMERS;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */
public class CustomerServiceTest extends AbstractServiceTest {

    @Autowired
    protected CustomerService customerService;


    @Test
    public void testGet(){
        MATCHER.assertEquals(customerService.get(CUSTOMER_ID), TEST_CUSTOMERS.get(1));
    }


    @Test
    public void testGetNotFound() {
        thrown.expect(NotFoundException.class);
        customerService.get(1);
    }

}
