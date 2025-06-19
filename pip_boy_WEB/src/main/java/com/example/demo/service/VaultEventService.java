package com.example.demo.service;

import model.VaultEvent;
import com.example.demo.repository.VaultEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class VaultEventService {

    private final VaultEventRepository eventRepository;

    public VaultEventService(VaultEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<VaultEvent> getActiveEvents() {
        return eventRepository.findByResolvedFalseOrderByEventTimeDesc();
    }

    public List<VaultEvent> getEventsByVaultNumber(int vaultNumber) {
        return eventRepository.findByVaultVaultNumberOrderByEventTimeDesc(vaultNumber);
    }

    public List<VaultEvent> getCriticalEvents() {
        return eventRepository.findBySeverityGreaterThanEqualOrderByEventTimeDesc(3);
    }

    public VaultEvent createEvent(VaultEvent event) {
        event.setEventTime(LocalDateTime.now());
        event.setResolved(false);
        return eventRepository.save(event);
    }

    public void resolveEvent(int eventId) {
        eventRepository.findById(eventId).ifPresent(event -> {
            event.setResolved(true);
            eventRepository.save(event);
        });
    }
}