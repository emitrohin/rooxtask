package ru.emitrohin.roox.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.emitrohin.roox.model.PartnerMapping;
import ru.emitrohin.roox.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static ru.emitrohin.roox.testdata.PartnerMappingTestData.*;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */
public class PartnerMappingServiceTest extends AbstractServiceTest {

    @Autowired
    private PartnerMappingService partnerMappingService;

    private final int UNKNOWN_ID = 25;

    @Test
    public void testGet() {
        MATCHER.assertEquals(partnerMappingService.get(100007, CUSTOMER_ID), TEST_MAPPINGS.get(3));
    }

    @Test
    public void testGetNotFound()
    {
        thrown.expect(NotFoundException.class);
        partnerMappingService.get(100010, CUSTOMER_ID);
    }

    @Test
    public void testGetNotFoundUnauthorized() {
        thrown.expect(NotFoundException.class);
        partnerMappingService.get(100009, CUSTOMER_ID);
    }

    @Test
    public void testGetAllByCustomerId()
    {
        Collection<PartnerMapping> expected = TEST_MAPPINGS.stream()
                .filter(x -> x.getCustomer().getId() == CUSTOMER_ID)
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(expected, partnerMappingService.findAllByCustomerId(CUSTOMER_ID));
    }

    @Test
    public void testDelete() throws Exception {
        partnerMappingService.delete(100007, CUSTOMER_ID);
        Collection<PartnerMapping> expected = Collections.singletonList(TEST_MAPPINGS.get(4));

        MATCHER.assertCollectionEquals(expected, partnerMappingService.findAllByCustomerId(CUSTOMER_ID));
    }

    @Test
    public void testDeleteUnknownId() {
        thrown.expect(NotFoundException.class);
        partnerMappingService.delete(UNKNOWN_ID, CUSTOMER_ID);
    }

    @Test
    public void testDeleteUnauthorized() {
        thrown.expect(NotFoundException.class);
        partnerMappingService.delete(TEST_MAPPINGS.get(0).getId(), CUSTOMER_ID);
    }


    @Test
    public void testCreate() throws Exception {
        PartnerMapping created = getCreated();
        partnerMappingService.create(created, CUSTOMER_ID);

        Collection<PartnerMapping> expected = Arrays.asList(TEST_MAPPINGS.get(3), TEST_MAPPINGS.get(4), created);

        MATCHER.assertCollectionEquals(expected, partnerMappingService.findAllByCustomerId(CUSTOMER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        PartnerMapping updated = getUpdated();
        partnerMappingService.update(updated, CUSTOMER_ID);
        MATCHER.assertEquals(updated, partnerMappingService.get(100008, CUSTOMER_ID));
    }

    @Test
    public void testUpdateNotFoundUnauthorized() throws Exception {
        thrown.expect(NotFoundException.class);
        PartnerMapping item = partnerMappingService.get(100008, CUSTOMER_ID);
        partnerMappingService.update(item, CUSTOMER_ID + 10);
    }
}
