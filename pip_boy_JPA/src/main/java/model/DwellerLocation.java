package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the dweller_location database table.
 * 
 */
@Entity
@Table(name="dweller_location")
@NamedQuery(name="DwellerLocation.findAll", query="SELECT d FROM DwellerLocation d")
public class DwellerLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dweller_id")
	private int dwellerId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;

	//bi-directional one-to-one association to VaultDweller
	@OneToOne
	@JoinColumn(name="dweller_id")
	private VaultDweller vaultDweller;

	public DwellerLocation() {
	}

	public int getDwellerId() {
		return this.dwellerId;
	}

	public void setDwellerId(int dwellerId) {
		this.dwellerId = dwellerId;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public VaultDweller getVaultDweller() {
		return this.vaultDweller;
	}

	public void setVaultDweller(VaultDweller vaultDweller) {
		this.vaultDweller = vaultDweller;
	}

}