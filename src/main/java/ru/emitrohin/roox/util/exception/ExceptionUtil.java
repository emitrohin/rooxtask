package ru.emitrohin.roox.util.exception;

import ru.emitrohin.roox.model.Customer;
import ru.emitrohin.roox.model.PartnerMapping;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */
public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String message) {
        checkNotFound(object != null, message);
        return object;
    }

    public static void checkNotFound(boolean found, String message) {
        if (!found) {
            throw new NotFoundException("Entity wasn't found: " + message);
        }
    }

    public static Customer checkNotFoundWithAuthorization(Customer customer, int authorizedCustomerId, String message)
    {
        checkNotFound(customer, message);

        if (customer.getId() != authorizedCustomerId)
        {
            throw new NotFoundException("Entity wasn't found: " + message);
        }

        return customer;
    }

    public static PartnerMapping checkNotFoundWithAuthorization(PartnerMapping partnerMapping, int authorizedCustomerId, String message)
    {
        checkNotFound(partnerMapping, message);

        if (partnerMapping.getCustomer().getId() != authorizedCustomerId)
        {
            throw new NotFoundException("Entity wasn't found: " + message);
        }

        return partnerMapping;
    }
}
