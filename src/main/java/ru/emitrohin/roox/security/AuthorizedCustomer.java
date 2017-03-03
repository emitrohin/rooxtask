package ru.emitrohin.roox.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import ru.emitrohin.roox.model.Customer;

import java.util.Collections;

import static java.util.Objects.requireNonNull;


/**
 * Secured class for customer authorization
 *
 * @author Evgeniy Mitrokhin
 */
public class AuthorizedCustomer extends User {

    private final Customer customer;

    /**
     * Creates AuthorizedCustomer object from customer object
     *
     * @param customer
     */
    public AuthorizedCustomer(Customer customer) {
        super(customer.getLogin(), customer.getPassword(), customer.isEnabled(), true, true, true, Collections.emptyList());
        this.customer = customer;
    }

    private static AuthorizedCustomer safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedCustomer) ? (AuthorizedCustomer) principal : null;
    }

    private static AuthorizedCustomer get() {
        AuthorizedCustomer customer = safeGet();
        requireNonNull(customer, "No authorized customer was found");
        return customer;
    }

    /**
     * Returns an id of authenticated customer
     *
     * @return authenticated customer id
     */
    public static int id() {
        return get().customer.getId();
    }
}
