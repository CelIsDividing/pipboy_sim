package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TicketMessageRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.VaultDwellerRepository;

import model.Ticket;
import model.TicketMessage;
import model.VaultDweller;
import resources.TicketStatus;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private TicketMessageRepository ticketMessageRepository;
    
    @Autowired
    private VaultDwellerRepository vaultDwellerRepository;
    
    public Ticket createTicket(Ticket ticket, VaultDweller submitter) {
        ticket.setSubmitter(submitter);
        ticket.setStatus(TicketStatus.OPEN);
        return ticketRepository.save(ticket);
    }
    
    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
    }
    
    public TicketMessage addResponse(Long ticketId, String content, VaultDweller sender, boolean finalize) {
        Ticket ticket = ticketRepository.findById(ticketId)
            .orElseThrow(() -> new RuntimeException("Ticket not found"));
        
        TicketMessage message = new TicketMessage();
        message.setContent(content);
        message.setSender(sender);
        message.setTicket(ticket);
        ticketMessageRepository.save(message);
        
        if (finalize) {
            ticket.setStatus(TicketStatus.RESOLVED);
        } else {
            ticket.setStatus(TicketStatus.AWAITING_RESPONSE);
        }
        
        ticketRepository.save(ticket);
        
        return message;
    }
    
    public List<Ticket> getUserTickets(VaultDweller user) {
        return ticketRepository.findBySubmitter(user);
    }
    
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAllByOrderByUpdatedAtDesc();
    }
    
    public List<Ticket> getOpenTickets() {
        return ticketRepository.findByStatusNot(TicketStatus.RESOLVED);
    }
    
    public List<TicketMessage> getMessagesBySender(VaultDweller sender) {
        return ticketMessageRepository.findBySenderDwellerId(sender.getDwellerId());
    }
    
    public List<TicketMessage> getMessagesForTicket(Long ticketId) {
        return ticketMessageRepository.findByTicketId(ticketId);
    }
}
