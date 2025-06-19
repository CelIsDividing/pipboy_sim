package model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the vault database table.
 * 
 */
@Entity
@NamedQuery(name="Vault.findAll", query="SELECT v FROM Vault v")
public class Vault implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vault_number")
	private int vaultNumber;

	private String location;

	@Column(name = "radiation_level", precision = 5, scale = 2)
    private BigDecimal radiationLevel = new BigDecimal("0.00");
	
	@Column(name="overseer_name")
	private String overseerName;

	private int population;

	private String status;

	//bi-directional many-to-one association to VaultDweller
	@OneToMany(mappedBy="vault")
	private List<VaultDweller> vaultDwellers;

	//bi-directional many-to-one association to VaultEvent
	@OneToMany(mappedBy="vault")
	private List<VaultEvent> vaultEvents;

	public Vault() {
	}

	public int getVaultNumber() {
		return this.vaultNumber;
	}

	public void setVaultNumber(int vaultNumber) {
		this.vaultNumber = vaultNumber;
	}
	
	public BigDecimal getRadiationLevel() {
        return radiationLevel;
    }
    
    public void setRadiationLevel(BigDecimal radiationLevel) {
        this.radiationLevel = radiationLevel;
    }

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOverseerName() {
		return this.overseerName;
	}

	public void setOverseerName(String overseerName) {
		this.overseerName = overseerName;
	}

	public int getPopulation() {
		return this.population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<VaultDweller> getVaultDwellers() {
		return this.vaultDwellers;
	}

	public void setVaultDwellers(List<VaultDweller> vaultDwellers) {
		this.vaultDwellers = vaultDwellers;
	}

	public VaultDweller addVaultDweller(VaultDweller vaultDweller) {
		getVaultDwellers().add(vaultDweller);
		vaultDweller.setVault(this);

		return vaultDweller;
	}

	public VaultDweller removeVaultDweller(VaultDweller vaultDweller) {
		getVaultDwellers().remove(vaultDweller);
		vaultDweller.setVault(null);

		return vaultDweller;
	}

	public List<VaultEvent> getVaultEvents() {
		return this.vaultEvents;
	}

	public void setVaultEvents(List<VaultEvent> vaultEvents) {
		this.vaultEvents = vaultEvents;
	}

	public VaultEvent addVaultEvent(VaultEvent vaultEvent) {
		getVaultEvents().add(vaultEvent);
		vaultEvent.setVault(this);

		return vaultEvent;
	}

	public VaultEvent removeVaultEvent(VaultEvent vaultEvent) {
		getVaultEvents().remove(vaultEvent);
		vaultEvent.setVault(null);

		return vaultEvent;
	}

}