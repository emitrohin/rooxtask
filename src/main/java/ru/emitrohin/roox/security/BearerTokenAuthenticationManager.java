package ru.emitrohin.roox.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import ru.emitrohin.roox.service.CustomerService;
import ru.emitrohin.roox.util.exception.ExceptionUtil;

/**
 * Author: E_Mitrohin
 * Date:   02.03.2017.
 */


public class BearerTokenAuthenticationManager implements AuthenticationManager {

    private CustomerService customerService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (authentication instanceof BearerTokenAuthentication)
        {
            return processAuthentication((BearerTokenAuthentication) authentication);
        }
        else {
            authentication.setAuthenticated(false);
            return authentication;
        }

    }

    private BearerTokenAuthentication processAuthentication(BearerTokenAuthentication authentication) {
        String token = authentication.getToken();
        AuthorizedCustomer customer = customerService.loadUserByUsername(token);

        ExceptionUtil.checkNotFound(customer, "User " + token + "wasn't found");

        if (customer.isEnabled())
        {
            return new BearerTokenAuthentication(authentication.getToken(), true, customer);
        }
        else
        {
            throw new AuthenticationServiceException("User is disabled");
        }
    }
}
