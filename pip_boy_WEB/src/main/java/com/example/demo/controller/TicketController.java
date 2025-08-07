package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.VaultDwellerRepository;
import com.example.demo.service.TicketService;

import model.VaultDweller;
import model.Ticket;
import model.TicketMessage;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    
    @Autowired
    private VaultDwellerRepository vaultDwellerRepository;
    
    @GetMapping
    public String viewTickets(Model model, Authentication authentication) {
    	if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
    	
        VaultDweller user = vaultDwellerRepository.findByUsername(authentication.getName());
        
        if ("ADMIN".equals(user.getSecurityClearance())) {
        	model.addAttribute("tickets", ticketService.getAllTickets());
            return "tickets/admin_tickets";
            
        } else {
        	model.addAttribute("tickets", ticketService.getUserTickets(user));
            return "tickets/dweller_tickets";
        }
    }
    
    @GetMapping("/new")
    public String showTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "tickets/new_ticket";
    }
    
    @PostMapping("/new")
    public String submitTicket(@ModelAttribute Ticket ticket, Authentication authentication) {
    	if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
    	
        VaultDweller user = vaultDwellerRepository.findByUsername(authentication.getName());
        ticketService.createTicket(ticket, user);
        return "redirect:/tickets";
    }
    
    @GetMapping("/{id}")
    public String viewTicket(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.findById(id);
        model.addAttribute("ticket", ticket);
        model.addAttribute("message", new TicketMessage());
        return "tickets/ticket_details";
    }
    
    @PostMapping("/{id}/respond")
    public String respondToTicket(@PathVariable Long id, 
                                @RequestParam String content,
                                @RequestParam(required = false) boolean finalize,
                                Authentication authentication) {
    	
    	if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
    	
        VaultDweller user = vaultDwellerRepository.findByUsername(authentication.getName());
        ticketService.addResponse(id, content, user, finalize);
        return "redirect:/tickets/" + id;
    }
}
