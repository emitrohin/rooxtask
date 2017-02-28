package ru.emitrohin.roox.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.emitrohin.roox.AuthorizedCustomer;
import ru.emitrohin.roox.model.Customer;
import ru.emitrohin.roox.service.CustomerService;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */

@RestController
@RequestMapping(value = CustomerRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestController extends BaseController {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected static final String REST_URL = BaseController.REST_URL + "/customer";

    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public Customer get(@PathVariable("customerId") int customerId) {
        log.info("Customer get " + customerId);
        int authorizedId = 100001; //AuthorizedCustomer.id();
        return customerService.get(customerId, authorizedId);
    }

    @GetMapping("/@me")
    public Customer get() {
        log.info("Customer get by @me");
        int authorizedId = 100001; //AuthorizedCustomer.id();
        return customerService.get(authorizedId);
    }
}
