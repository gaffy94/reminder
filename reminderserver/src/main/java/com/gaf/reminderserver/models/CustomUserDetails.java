package com.gaf.reminderserver.models;

import com.gaf.reminderserver.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetails implements UserDetails {
    public User user;

    public CustomUserDetails(final User _user) {
        this.user = _user;
    }

    public CustomUserDetails() {
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> _grntdAuths = new HashSet<GrantedAuthority>();
        return _grntdAuths;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        if (this.user == null) {
            return null;
        }
        return this.user.getEmail();
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

    public User getUser() {
        return user;
    }


    @Override
    public String toString() {
        return "CustomUserDetails [user=" + user + "]";

    }

    public void setUser(User user) {
        this.user = user;
    }

}