package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the radio_station database table.
 * 
 */
@Entity
@Table(name="radio_station")
@NamedQuery(name="RadioStation.findAll", query="SELECT r FROM RadioStation r")
public class RadioStation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="station_id")
	private int stationId;

	private BigDecimal frequency;

	@Column(name="is_online")
	private byte isOnline;

	private String location;

	private String name;

	//bi-directional many-to-one association to RadioMessage
	@OneToMany(mappedBy="radioStation")
	private List<RadioMessage> radioMessages;

	public RadioStation() {
	}

	public int getStationId() {
		return this.stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public BigDecimal getFrequency() {
		return this.frequency;
	}

	public void setFrequency(BigDecimal frequency) {
		this.frequency = frequency;
	}

	public byte getIsOnline() {
		return this.isOnline;
	}

	public void setIsOnline(byte isOnline) {
		this.isOnline = isOnline;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RadioMessage> getRadioMessages() {
		return this.radioMessages;
	}

	public void setRadioMessages(List<RadioMessage> radioMessages) {
		this.radioMessages = radioMessages;
	}

	public RadioMessage addRadioMessage(RadioMessage radioMessage) {
		getRadioMessages().add(radioMessage);
		radioMessage.setRadioStation(this);

		return radioMessage;
	}

	public RadioMessage removeRadioMessage(RadioMessage radioMessage) {
		getRadioMessages().remove(radioMessage);
		radioMessage.setRadioStation(null);

		return radioMessage;
	}

}