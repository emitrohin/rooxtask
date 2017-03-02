package ru.emitrohin.roox.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: E_Mitrohin
 * Date:   02.03.2017.
 */
public class BearerTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public BearerTokenAuthenticationFilter() {
        super("/**");
        setAuthenticationSuccessHandler((request, response, authentication) ->
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getRequestDispatcher(request.getServletPath() + request.getPathInfo()).forward(request, response);
        });
        setAuthenticationFailureHandler((request, response, authenticationException) -> {
            response.getOutputStream().print(authenticationException.getMessage());
        });
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader("Authorization");

        if (token == null) {
            BearerTokenAuthentication authentication = new BearerTokenAuthentication(null);
            authentication.setAuthenticated(false);
            return authentication;
        }

        BearerTokenAuthentication tokenAuthentication = new BearerTokenAuthentication(token);
        Authentication authentication = getAuthenticationManager().authenticate(tokenAuthentication);

        return authentication;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
}
