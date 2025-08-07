package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ticket_messages database table.
 * 
 */
@Entity
@Table(name="ticket_messages")
@NamedQuery(name="TicketMessage.findAll", query="SELECT t FROM TicketMessage t")
public class TicketMessage implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="message_id")
    private Long id;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    
    @ManyToOne
    @JoinColumn(name="sender_id", nullable = false)
    private VaultDweller sender;
    
    @ManyToOne
    @JoinColumn(name="ticket_id", nullable = false)
    private Ticket ticket;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="sent_at", nullable = false, updatable = false)
    private Date sentAt;
    
    @PrePersist
    protected void onCreate() {
        sentAt = new Date();
    }

	public TicketMessage() {
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public VaultDweller getSender() {
        return this.sender;
    }

    public void setSender(VaultDweller sender) {
        this.sender = sender;
    }

    public Ticket getTicket() {
        return this.ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Date getSentAt() {
        return this.sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

}