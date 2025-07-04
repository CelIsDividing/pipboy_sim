package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repository.VaultRepository;

@Controller
public class IndexController {

	@Autowired
	private VaultRepository vaultRepository;

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("vaults", vaultRepository.findAll());
		return "index";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
}