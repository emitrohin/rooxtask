package ru.emitrohin.roox.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.emitrohin.roox.model.Customer;
import ru.emitrohin.roox.service.AbstractServiceTest;
import ru.emitrohin.roox.service.CustomerService;
import ru.emitrohin.roox.util.exception.NotFoundException;
import ru.emitrohin.roox.web.CustomerRestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
      /*  mockMvc.perform(get(REST_URL + USER_ID))
                //.with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(TEST_CUSTOMERS.get(1)));*/
    }


    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + (USER_ID + 100)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}
