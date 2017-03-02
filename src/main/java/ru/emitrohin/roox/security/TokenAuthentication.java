package ru.emitrohin.roox.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ru.emitrohin.roox.security.AuthorizedCustomer;

import java.util.Collection;

/**
 * Author: E_Mitrohin
 * Date:   02.03.2017.
 */
public class TokenAuthentication implements Authentication {

    private String token;
    private boolean isAuthenticated;
    private AuthorizedCustomer principal;


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

    public String getToken() {
        return token;
    }
}
