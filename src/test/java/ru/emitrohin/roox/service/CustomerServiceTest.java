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

    protected final int AUTH_ID = 100001;

    @Test
    public void testGet() throws Exception {
        MATCHER.assertEquals(customerService.get(AUTH_ID), TEST_CUSTOMERS.get(1));
    }

    @Test
    public void testGetAuthorized() throws Exception {
        MATCHER.assertEquals(customerService.get(AUTH_ID, AUTH_ID), TEST_CUSTOMERS.get(1));
    }


    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        customerService.get(1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFoundUnauthorized() throws Exception {
        customerService.get(AUTH_ID, 100002);
    }


}
