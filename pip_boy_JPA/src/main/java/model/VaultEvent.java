package model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;


/**
 * The persistent class for the vault_event database table.
 * 
 */
@Entity
@Table(name="vault_event")
@NamedQuery(name="VaultEvent.findAll", query="SELECT v FROM VaultEvent v")
public class VaultEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="event_id")
	private int eventId;

	@Lob
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="event_time")
	private LocalDateTime eventTime;

	@Column(name="event_type")
	private String eventType;

	private int severity;
	
	private boolean resolved;

	//bi-directional many-to-one association to Vault
	@ManyToOne
	@JoinColumn(name="vault_number")
	private Vault vault;

	public VaultEvent() {
	}

	public int getEventId() {
		return this.eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
	
	public boolean isResolved() {
        return resolved;
    }

	public LocalDateTime getEventTime() {
		return this.eventTime;
	}

	public void setEventTime(LocalDateTime localDateTime) {
		this.eventTime = localDateTime;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public int getSeverity() {
		return this.severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public Vault getVault() {
		return this.vault;
	}

	public void setVault(Vault vault) {
		this.vault = vault;
	}

}