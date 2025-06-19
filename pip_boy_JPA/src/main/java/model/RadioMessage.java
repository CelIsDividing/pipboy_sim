package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the radio_message database table.
 * 
 */
@Entity
@Table(name="radio_message")
@NamedQuery(name="RadioMessage.findAll", query="SELECT r FROM RadioMessage r")
public class RadioMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_id")
	private int messageId;

	@Lob
	private String content;

	@Column(name="is_emergency")
	private byte isEmergency;

	private String sender;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	//bi-directional many-to-one association to RadioStation
	@ManyToOne
	@JoinColumn(name="station_id")
	private RadioStation radioStation;

	public RadioMessage() {
	}

	public int getMessageId() {
		return this.messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public byte getIsEmergency() {
		return this.isEmergency;
	}

	public void setIsEmergency(byte isEmergency) {
		this.isEmergency = isEmergency;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public RadioStation getRadioStation() {
		return this.radioStation;
	}

	public void setRadioStation(RadioStation radioStation) {
		this.radioStation = radioStation;
	}

}