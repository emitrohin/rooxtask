package ru.emitrohin.roox.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.emitrohin.roox.service.CustomerService;
import ru.emitrohin.roox.util.exception.ErrorInfo;
import ru.emitrohin.roox.util.exception.ExceptionUtil;
import ru.emitrohin.roox.web.json.JsonUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: E_Mitrohin
 * Date:   02.03.2017.
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private CustomerService customerService;

    public TokenAuthenticationFilter(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization");

            validateToken(token);

            AuthorizedCustomer customer = customerService.loadUserById(parseToken(token));
            ExceptionUtil.checkNotFound(customer, "User " + token + " wasn't found");

            TokenAuthentication auth = new TokenAuthentication(token, true, customer);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            writeErrorResponse(request, response, e);
        }
    }

    private void validateToken(String token){
        if (token == null || token.isEmpty() || !token.contains("Bearer")) {
            throw new AuthenticationException("Access denied"){};
        }
    }

    private void writeErrorResponse(HttpServletRequest request, HttpServletResponse response,  Exception e) throws IOException {
        ErrorInfo errorInfo = new ErrorInfo(request.getRequestURL(), e);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(JsonUtil.writeValue(errorInfo));
    }

    private int parseToken(String token){
        try {
            return Integer.parseInt(token.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new AuthenticationException("Access denied"){};
        }
    }

}
