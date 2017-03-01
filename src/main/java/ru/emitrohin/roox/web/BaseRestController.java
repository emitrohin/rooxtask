package ru.emitrohin.roox.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.emitrohin.roox.util.exception.NotFoundException;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */
abstract class BaseRestController {
    static final String REST_URL = "/rest/v1.0";
    protected final static Logger LOG = LoggerFactory.getLogger(BaseRestController.class);

    protected void checkAuthorizedId(int customerId) {
        if (customerId != 100001) //AuthorizedCustomer.id();
        {
            throw new NotFoundException("");
        }
    }

}
