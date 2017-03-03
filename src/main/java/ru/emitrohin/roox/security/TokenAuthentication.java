package ru.emitrohin.roox.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Custom wrapper for Authentication to hold token and AuthorizedCustomer information
 *
 * @author Evgeniy Mitrokhin
 */
class TokenAuthentication implements Authentication {

    private final String token;
    private final AuthorizedCustomer principal;
    private boolean isAuthenticated;


    public TokenAuthentication(String token, boolean isAuthenticated, AuthorizedCustomer principal) {
        this.token = token;
        this.isAuthenticated = isAuthenticated;
        this.principal = principal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        if (principal != null)
            return principal.getUsername();
        else
            return null;
    }
}
