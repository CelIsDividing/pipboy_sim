package model;

import java.io.Serializable;
import jakarta.persistence.*;
import resources.TicketStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tickets database table.
 * 
 */
@Entity
@Table(name="tickets")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ticket_id")
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status = TicketStatus.OPEN;
    
    @ManyToOne
    @JoinColumn(name="submitter_id", nullable = false)
    private VaultDweller submitter;
    
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<TicketMessage> messages = new ArrayList<>();
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable = false, updatable = false)
    private Date createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at", nullable = false)
    private Date updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
	
	public Ticket() {
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return this.status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public VaultDweller getSubmitter() {
        return this.submitter;
    }

    public void setSubmitter(VaultDweller submitter) {
        this.submitter = submitter;
    }

    public List<TicketMessage> getMessages() {
        return this.messages;
    }

    public void setMessages(List<TicketMessage> messages) {
        this.messages = messages;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Convenience methods for bidirectional relationship
    public TicketMessage addMessage(TicketMessage message) {
        getMessages().add(message);
        message.setTicket(this);
        return message;
    }

    public TicketMessage removeMessage(TicketMessage message) {
        getMessages().remove(message);
        message.setTicket(null);
        return message;
    }
	
}