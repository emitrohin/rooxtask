package ru.emitrohin.roox.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.emitrohin.roox.model.Customer;
import ru.emitrohin.roox.web.CustomerRestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.emitrohin.roox.testdata.CustomerTestData.MATCHER;
import static ru.emitrohin.roox.testdata.CustomerTestData.TEST_CUSTOMERS;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */
public class CustomerControllerTest extends AbstractControllerTest {

    private static final String REST_URL = CustomerRestController.REST_URL + '/';

    @Test
    public void testGet() throws Exception {
        Customer filteredCustomer = Customer.fromCustomer(TEST_CUSTOMERS.get(1));
        filteredCustomer.setLogin(null);
        filteredCustomer.setPassword(null);

        mockMvc.perform(get(REST_URL + CUSTOMER_ID))
                //.with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(filteredCustomer));
    }


    @Test
    public void testGetNotFound() throws Exception {
        int impossibleId = CUSTOMER_ID + 100;
        mockMvc.perform(get(REST_URL + impossibleId))
                .andExpect(status().isNotFound());
    }


    @Test //Сделать авторизацию, чтобы проверить
    public void testGetNotFoundWhenUnauthorized() throws Exception {
        mockMvc.perform(get(REST_URL + (CUSTOMER_ID + 100)))
                .andExpect(status().isNotFound());
    }

}
