package com.example.demo.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {
    public static void main(String[] args) {
        String password = "12345";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Encoded password: " + passwordEncoder.encode(password));
    }
}