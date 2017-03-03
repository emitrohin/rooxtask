package ru.emitrohin.roox.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.emitrohin.roox.model.PartnerMapping;
import ru.emitrohin.roox.service.PartnerMappingService;
import ru.emitrohin.roox.web.CustomerRestController;
import ru.emitrohin.roox.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private static final int MAPPING_ID = 100008;

    @Autowired
    private PartnerMappingService partnerMappingService;

    //get all tests

    @Test
    public void getAllById() throws Exception{

        List<PartnerMapping> expected = TEST_MAPPINGS.stream()
                .filter(x -> x.getCustomer().getId() == CUSTOMER_ID)
                .collect(Collectors.toList());

        mockMvc.perform(get(REST_URL + CUSTOMER_ID + "/partner-mappings")
                .header("Authorization", "Bearer " + CUSTOMER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(expected));
    }

    @Test
    public void getAllByIdNotFound() throws Exception{
        int notYourId = TEST_CUSTOMERS.get(0).getId();
        mockMvc.perform(get(REST_URL + notYourId + "/partner-mappings")
                .header("Authorization", "Bearer " + CUSTOMER_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllByMe() throws Exception{

        List<PartnerMapping> expected = TEST_MAPPINGS.stream()
                .filter(x -> x.getCustomer().getId() == CUSTOMER_ID)
                .collect(Collectors.toList());

        mockMvc.perform(get(REST_URL + "@me/partner-mappings")
                .header("Authorization", "Bearer " + CUSTOMER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(expected));
    }

    //get tests

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get(REST_URL + CUSTOMER_ID + "/partner-mappings/" + MAPPING_ID)
                .header("Authorization", "Bearer " + CUSTOMER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(TEST_MAPPINGS.get(4)));
    }

    @Test
    public void testGetByNotYourCustomerIdNotFound() throws Exception {
        int notYourId = TEST_CUSTOMERS.get(3).getId();
        mockMvc.perform(get(REST_URL + notYourId + "/partner-mappings/" + MAPPING_ID)
                .header("Authorization", "Bearer " + CUSTOMER_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetByNotYourMappingIdNotFound() throws Exception {
        int notYourMappingId = TEST_MAPPINGS.get(0).getId();
        mockMvc.perform(get(REST_URL + CUSTOMER_ID + "/partner-mappings/" + notYourMappingId)
                .header("Authorization", "Bearer " + CUSTOMER_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetByMe() throws Exception {
        mockMvc.perform(get(REST_URL + CUSTOMER_ID + "/partner-mappings/" + MAPPING_ID)
                .header("Authorization", "Bearer " + CUSTOMER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(TEST_MAPPINGS.get(4)));
    }

    //create test

    @Test
    public void testCreateById() throws Exception {
        PartnerMapping expected = new PartnerMapping(null, "NewId", "NextNewId", "Test", "Test", "Test", null, TEST_CUSTOMERS.get(0));
        ResultActions action = mockMvc.perform(post(REST_URL + CUSTOMER_ID + "/partner-mappings/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + CUSTOMER_ID)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        PartnerMapping returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);

        Collection<PartnerMapping> expectedList = Arrays.asList(returned, TEST_MAPPINGS.get(3), TEST_MAPPINGS.get(4));
        MATCHER.assertCollectionEquals(expectedList, partnerMappingService.findAllByCustomerId(CUSTOMER_ID));
    }


    @Test
    public void testCreateByMe() throws Exception {
        PartnerMapping expected = new PartnerMapping(null, "NewId", "NextNewId", "Test", "Test", "Test", null, TEST_CUSTOMERS.get(0));
        ResultActions action = mockMvc.perform(post(REST_URL + "@me/partner-mappings/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + CUSTOMER_ID)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        PartnerMapping returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);

        Collection<PartnerMapping> expectedList = Arrays.asList(returned, TEST_MAPPINGS.get(3), TEST_MAPPINGS.get(4));
        MATCHER.assertCollectionEquals(expectedList, partnerMappingService.findAllByCustomerId(CUSTOMER_ID));
    }

    @Test
    public void testCreateByIdUnauthorized() throws Exception {
        PartnerMapping expected = new PartnerMapping(null, "NewId", "NextNewId", "Test", "Test", "Test", null, TEST_CUSTOMERS.get(0));

        mockMvc.perform(post(REST_URL + CUSTOMER_ID + "/partner-mappings/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + (CUSTOMER_ID + 3))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isUnauthorized());
    }

    //update tests
    @Test
    public void testUpdateById() throws Exception {
        PartnerMapping updated = PartnerMapping.from(TEST_MAPPINGS.get(4));
        updated.setFirstName("Updated");
        updated.setPartnerId("supertoken");
        mockMvc.perform(put(REST_URL + CUSTOMER_ID + "/partner-mappings/" + MAPPING_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + CUSTOMER_ID)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, partnerMappingService.get(MAPPING_ID, CUSTOMER_ID));
    }

    @Test
    public void testUpdateByMe() throws Exception {
        PartnerMapping updated = PartnerMapping.from(TEST_MAPPINGS.get(4));
        updated.setFirstName("Updated");
        updated.setPartnerId("supertoken");
        mockMvc.perform(put(REST_URL + "@me/partner-mappings/" + MAPPING_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + CUSTOMER_ID)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, partnerMappingService.get(MAPPING_ID, CUSTOMER_ID));
    }

    @Test
    public void testUpdateByIdUnauthorized() throws Exception {
        PartnerMapping updated = PartnerMapping.from(TEST_MAPPINGS.get(4));
        updated.setFirstName("Updated");
        updated.setPartnerId("supertoken");

        mockMvc.perform(put(REST_URL + CUSTOMER_ID + "/partner-mappings/" + MAPPING_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + (CUSTOMER_ID + 3))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateByIdNotFound() throws Exception {
        PartnerMapping updated = PartnerMapping.from(TEST_MAPPINGS.get(4));
        updated.setFirstName("Updated");
        updated.setPartnerId("supertoken");

        mockMvc.perform(put(REST_URL + CUSTOMER_ID + "/partner-mappings/" + (MAPPING_ID - 2))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + CUSTOMER_ID)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNotFound());
    }


    //delete tests
    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete(REST_URL + CUSTOMER_ID + "/partner-mappings/" + MAPPING_ID)
                .header("Authorization", "Bearer " + CUSTOMER_ID))
                .andExpect(status().isOk());

        MATCHER.assertCollectionEquals(Collections.singletonList(TEST_MAPPINGS.get(3)), partnerMappingService.findAllByCustomerId(CUSTOMER_ID));
    }

    @Test
    public void testDeleteByNotYourCustomerId() throws Exception {
        int notYourId = TEST_CUSTOMERS.get(3).getId();

        mockMvc.perform(delete(REST_URL + notYourId + "/partner-mappings/" + MAPPING_ID)
                .header("Authorization", "Bearer " + CUSTOMER_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteByNotYourMappingId() throws Exception {
        int notYourMappingId = TEST_MAPPINGS.get(0).getId();

        mockMvc.perform(delete(REST_URL + CUSTOMER_ID + "/partner-mappings/" + notYourMappingId)
                .header("Authorization", "Bearer " + CUSTOMER_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteByMe() throws Exception {
        mockMvc.perform(delete(REST_URL + "@me/partner-mappings/" + MAPPING_ID)
                .header("Authorization", "Bearer " + CUSTOMER_ID))
                .andExpect(status().isOk());

        MATCHER.assertCollectionEquals(Collections.singletonList(TEST_MAPPINGS.get(3)), partnerMappingService.findAllByCustomerId(CUSTOMER_ID));
    }

}
