package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TerminalAuthService {

    public String generateVaultTecToken(String username) {
        // Simple token generation for demo purposes
        return "VT-" + UUID.randomUUID() + "-" + username.hashCode();
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("VT-");
    }
}