package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Ticket;
import model.VaultDweller;
import resources.TicketStatus;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findBySubmitter(VaultDweller submitter);
    List<Ticket> findAllByOrderByUpdatedAtDesc();
    List<Ticket> findByStatusNot(TicketStatus status);
}
