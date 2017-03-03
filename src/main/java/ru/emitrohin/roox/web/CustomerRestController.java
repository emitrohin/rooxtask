package ru.emitrohin.roox.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.emitrohin.roox.model.Customer;
import ru.emitrohin.roox.security.AuthorizedCustomer;
import ru.emitrohin.roox.service.CustomerService;

/**
 * Rest API for customer
 *
 * @author Evgeniy Mitrokhin
 */
@RestController
@RequestMapping(value = CustomerRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestController extends BaseRestController {

    public static final String REST_URL = BaseRestController.REST_URL + "/customers";

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * GET method.
     * Return customer json
     *
     * @param customerId - id of customer
     * @return customer from database
     */
    @GetMapping("/{customerId}")
    public Customer get(@PathVariable("customerId") int customerId) {
        LOG.info("Customer get {}", customerId);
        checkAuthorizedId(customerId);
        return customerService.get(customerId);
    }

    /**
     * GET method.
     * Return customer json
     * Uses @me literal. Id of customer is taken from AuthorizedCustomer.
     *
     * @return customer from database
     */
    @GetMapping("/@me")
    public Customer get() {
        int authorizedId = AuthorizedCustomer.id();
        LOG.info("Customer get by @me id {} ", authorizedId);
        return customerService.get(authorizedId);
    }
}
