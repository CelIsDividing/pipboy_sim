package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.repository.VaultDwellerRepository;

import model.VaultDweller;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private VaultDwellerRepository dwellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        VaultDweller dweller = dwellerRepository.findByUsername(username);
        
        return new CustomUserDetails(dweller);
    }
}