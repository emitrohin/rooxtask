package ru.emitrohin.roox.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.emitrohin.roox.model.PartnerMapping;
import ru.emitrohin.roox.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static ru.emitrohin.roox.testdata.PartnerMappingTestData.*;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */
public class PartnerMappingServiceTest extends AbstractServiceTest {

    @Autowired
    protected PartnerMappingService partnerMappingService;

    protected final int UNKNOWN_ID = 25;

    @Test
    public void testGet() {
        MATCHER.assertEquals(partnerMappingService.get(100007, USER_ID), TEST_MAPPINGS.get(3));
    }

    @Test
    public void testGetNotFound()
    {
        thrown.expect(NotFoundException.class);
        partnerMappingService.get(100010, USER_ID);
    }

    @Test
    public void testGetNotFoundUnauthorized() {
        thrown.expect(NotFoundException.class);
        partnerMappingService.get(100009, USER_ID);
    }

    @Test
    public void testGetAllByCustomerId()
    {
        Collection<PartnerMapping> expected = TEST_MAPPINGS.stream()
                .filter(x -> x.getCustomer().getId() == USER_ID)
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(expected, partnerMappingService.findAllByCustomerId(USER_ID));
    }

    @Test
    public void testDelete() throws Exception {
        partnerMappingService.delete(100007, USER_ID);
        Collection<PartnerMapping> expected = Arrays.asList(
                TEST_MAPPINGS.get(4)
        );

        MATCHER.assertCollectionEquals(expected, partnerMappingService.findAllByCustomerId(USER_ID));
    }

    @Test
    public void testDeleteNotFound() {
        thrown.expect(NotFoundException.class);
        partnerMappingService.delete(UNKNOWN_ID, USER_ID);
    }

    @Test
    public void testDeleteNotFoundUnauthorized() {
        thrown.expect(NotFoundException.class);
        partnerMappingService.delete(TEST_MAPPINGS.get(0).getId(), USER_ID);
    }


    @Test
    public void testCreate() throws Exception {
        PartnerMapping created = getCreated();
        partnerMappingService.create(created, USER_ID);

        Collection<PartnerMapping> expected = Arrays.asList(TEST_MAPPINGS.get(3), TEST_MAPPINGS.get(4), created);

        MATCHER.assertCollectionEquals(expected, partnerMappingService.findAllByCustomerId(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        PartnerMapping updated = getUpdated();
        partnerMappingService.update(updated, USER_ID);
        MATCHER.assertEquals(updated, partnerMappingService.get(100008, USER_ID));
    }

    @Test
    public void testUpdateNotFoundUnauthorized() throws Exception {
        thrown.expect(NotFoundException.class);
        PartnerMapping item = partnerMappingService.get(100008, USER_ID);
        partnerMappingService.update(item, USER_ID + 10);
    }
}
