package ru.emitrohin.roox.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.emitrohin.roox.util.exception.NotFoundException;

import static ru.emitrohin.roox.testdata.PartnerMappingTestData.MATCHER;
import static ru.emitrohin.roox.testdata.PartnerMappingTestData.TEST_MAPPINGS;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */
public class PartnerMappingServiceTest extends AbstractServiceTest {

    @Autowired
    protected PartnerMappingService partnerMappingService;

    protected final int AUTH_ID = 100001;

    @Test
    public void testGet() {
        MATCHER.assertEquals(partnerMappingService.get(100007, AUTH_ID), TEST_MAPPINGS.get(3));
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() {
        partnerMappingService.get(100010, AUTH_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFoundUnauthorized() {
        partnerMappingService.get(100009, AUTH_ID);
    }


}
