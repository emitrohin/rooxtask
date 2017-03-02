package ru.emitrohin.roox.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.emitrohin.roox.model.PartnerMapping;
import ru.emitrohin.roox.service.PartnerMappingService;
import ru.emitrohin.roox.web.CustomerRestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.emitrohin.roox.testdata.CustomerTestData.TEST_CUSTOMERS;
import static ru.emitrohin.roox.testdata.PartnerMappingTestData.MATCHER;
import static ru.emitrohin.roox.testdata.PartnerMappingTestData.TEST_MAPPINGS;

/**
 * Author: E_Mitrohin
 * Date:   02.03.2017.
 */
public class PartnerMappingControllerTest extends AbstractControllerTest {

    private static final String REST_URL = CustomerRestController.REST_URL + '/';
    private static int MAPPING_ID = 100008;

    @Autowired
    protected PartnerMappingService partnerMappingService;

    //get all tests

    @Test
    public void getAllById() throws Exception{

        List<PartnerMapping> expected = TEST_MAPPINGS.stream()
                .filter(x -> x.getCustomer().getId() == CUSTOMER_ID)
                .collect(Collectors.toList());

        mockMvc.perform(get(REST_URL + CUSTOMER_ID + "/partner-mappings"))
                //.with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(expected));
    }

    @Test //Auth
    public void getAllByIdNotFound() throws Exception{
        int notYourId = TEST_CUSTOMERS.get(0).getId();
        mockMvc.perform(get(REST_URL + notYourId + "/partner-mappings"))
                //.with(userHttpBasic(ADMIN)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllByMe() throws Exception{

        List<PartnerMapping> expected = TEST_MAPPINGS.stream()
                .filter(x -> x.getCustomer().getId() == CUSTOMER_ID)
                .collect(Collectors.toList());

        mockMvc.perform(get(REST_URL + "@me/partner-mappings"))
                //.with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(expected));
    }

    //get tests

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get(REST_URL + CUSTOMER_ID + "/partner-mappings/" + MAPPING_ID))
                //.with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(TEST_MAPPINGS.get(4)));
    }

    @Test
    public void testGetByNotYourCustomerIdNotFound() throws Exception {
        int notYourId = TEST_CUSTOMERS.get(3).getId();
        mockMvc.perform(get(REST_URL + notYourId + "/partner-mappings/" + MAPPING_ID))
                //.with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetByNotYourMappingIdNotFound() throws Exception {
        int notYourMappingId = TEST_MAPPINGS.get(0).getId();
        mockMvc.perform(get(REST_URL + CUSTOMER_ID + "/partner-mappings/" + notYourMappingId))
                //.with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetByMe() throws Exception {
        mockMvc.perform(get(REST_URL + CUSTOMER_ID + "/partner-mappings/" + MAPPING_ID))
                //.with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(TEST_MAPPINGS.get(4)));
    }

    //testcreate

    //testupdate

    //delete tests
    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete(REST_URL + CUSTOMER_ID + "/partner-mappings/" + MAPPING_ID))
                //.with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isOk());

        List<PartnerMapping> expected = TEST_MAPPINGS.stream()
                .filter(x -> x.getCustomer().getId() == CUSTOMER_ID)
                .collect(Collectors.toList());

        MATCHER.assertCollectionEquals(Collections.singletonList(TEST_MAPPINGS.get(3)), partnerMappingService.findAllByCustomerId(CUSTOMER_ID));
    }

    @Test //auth
    public void testDeleteByNotYourCustomerId() throws Exception {
        int notYourId = TEST_CUSTOMERS.get(3).getId();

        mockMvc.perform(delete(REST_URL + notYourId + "/partner-mappings/" + MAPPING_ID))
                //.with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isNotFound());
    }

    @Test //auth
    public void testDeleteByNotYourMappingId() throws Exception {
        int notYourMappingId = TEST_MAPPINGS.get(0).getId();

        mockMvc.perform(delete(REST_URL + CUSTOMER_ID + "/partner-mappings/" + notYourMappingId))
                //.with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isNotFound());
    }

    @Test //auth
    public void testDeleteByMe() throws Exception {
        mockMvc.perform(get(REST_URL + "@me/partner-mappings/" + MAPPING_ID))
                //.with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isOk());

        List<PartnerMapping> expected = TEST_MAPPINGS.stream()
                .filter(x -> x.getCustomer().getId() == CUSTOMER_ID)
                .collect(Collectors.toList());

        MATCHER.assertCollectionEquals(Collections.singletonList(TEST_MAPPINGS.get(3)), partnerMappingService.findAllByCustomerId(CUSTOMER_ID));
    }

}
