package ru.emitrohin.roox.security.t;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.emitrohin.roox.security.AuthorizedCustomer;
import ru.emitrohin.roox.service.CustomerService;
import ru.emitrohin.roox.util.exception.ExceptionUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: E_Mitrohin
 * Date:   02.03.2017.
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    private CustomerService customerService;

    public AuthenticationFilter(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(request, response);
            return;
        }

        AuthorizedCustomer customer = customerService.loadUserByUsername(token);
        ExceptionUtil.checkNotFound(customer, "User " + token + " wasn't found");

        TokenAuthentication auth = new TokenAuthentication(token, true, customer);
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

}
