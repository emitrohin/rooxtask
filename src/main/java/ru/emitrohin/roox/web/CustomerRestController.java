package ru.emitrohin.roox.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.emitrohin.roox.model.Customer;
import ru.emitrohin.roox.service.CustomerService;
import ru.emitrohin.roox.util.exception.NotFoundException;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */

@RestController
@RequestMapping(value = CustomerRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestController extends BaseRestController {

    public static final String REST_URL = BaseRestController.REST_URL + "/customer";

    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     *
     * @param customerId
     * @return
     */
    @GetMapping("/{customerId}")
    public Customer get(@PathVariable("customerId") int customerId) {
        LOG.info("Customer get {}", customerId);
        if (customerId != 100001) //AuthorizedCustomer.id();
        {
            throw new NotFoundException("");
        }
        return customerService.get(customerId);
    }

    @GetMapping("/@me")
    public Customer get() {
        int authorizedId = 100001; //AuthorizedCustomer.id();
        LOG.info("Customer get by @me id {} ", authorizedId);
        return customerService.get(authorizedId);
    }
}
