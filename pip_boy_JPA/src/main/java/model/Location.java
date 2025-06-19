package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="location_id")
	private int locationId;

	private int x_coord;
	private int y_coord;

	@Column(name="danger_level")
	private int dangerLevel;

	@Lob
	private String description;

	private String name;

	//bi-directional many-to-one association to DwellerLocation
	@OneToMany(mappedBy="location")
	private List<DwellerLocation> dwellerLocations;

	public Location() {
	}

	public int getLocationId() {
		return this.locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getX() {
		return this.x_coord;
	}
	
	public int getY() {
		return this.y_coord;
	}

	public void setX(int x_coord) {
		this.x_coord = x_coord;
	}
	
	public void setY(int y_coord) {
		this.y_coord = y_coord;
	}

	public int getDangerLevel() {
		return this.dangerLevel;
	}

	public void setDangerLevel(int dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DwellerLocation> getDwellerLocations() {
		return this.dwellerLocations;
	}

	public void setDwellerLocations(List<DwellerLocation> dwellerLocations) {
		this.dwellerLocations = dwellerLocations;
	}

	public DwellerLocation addDwellerLocation(DwellerLocation dwellerLocation) {
		getDwellerLocations().add(dwellerLocation);
		dwellerLocation.setLocation(this);

		return dwellerLocation;
	}

	public DwellerLocation removeDwellerLocation(DwellerLocation dwellerLocation) {
		getDwellerLocations().remove(dwellerLocation);
		dwellerLocation.setLocation(null);

		return dwellerLocation;
	}

}