package ru.emitrohin.roox.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.emitrohin.roox.security.AuthorizedCustomer;
import ru.emitrohin.roox.util.NotFoundException;

/**
 * Base super class with common fields and methods
 *
 * @author Evgeniy Mitrokhin
 */
abstract class BaseRestController {

    /**
     * Base url
     */
    protected static final String REST_URL = "/api/v1.0";
    final static Logger LOG = LoggerFactory.getLogger(BaseRestController.class);

    /**
     * Check where customerId equals AuthorizedCustomer id
     *
     * @throws NotFoundException for security reasons
     */
    void checkAuthorizedId(int customerId) {
        if (customerId != AuthorizedCustomer.id())
        {
            throw new NotFoundException("");
        }
    }

}
