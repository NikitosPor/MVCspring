package ru.otus.mvcspring.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class CustomUserDetails extends User implements UserDetails {

    // private static final long serialVersionUID = 1L;
    private final User user;

    public CustomUserDetails(User user) {
        super(user);
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       String role = (Objects.equals(this.user.getRole(), "ADMIN")) ? "ROLE_ADMIN" : "ROLE_USER";
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

}
