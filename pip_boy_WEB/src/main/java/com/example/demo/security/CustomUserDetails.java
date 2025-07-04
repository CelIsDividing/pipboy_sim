package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import model.VaultDweller;

public class CustomUserDetails implements UserDetails {

    private final VaultDweller dweller;

    public CustomUserDetails(VaultDweller dweller) {
        this.dweller = dweller;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + dweller.getSecurityClearance()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return dweller.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return dweller.getUsername();
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
    
    public VaultDweller getDweller() {
        return dweller;
    }
}