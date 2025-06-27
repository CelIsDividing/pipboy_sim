package com.example.demo.service;

import com.example.demo.repository.VaultDwellerRepository;
import model.VaultDweller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private VaultDwellerRepository dwellerRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        VaultDweller dweller = dwellerRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return new org.springframework.security.core.userdetails.User(
            dweller.getUsername(),
            dweller.getPassword(),
            getAuthorities(dweller)
        );
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(VaultDweller dweller) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + dweller.getRole()));
    }
}
