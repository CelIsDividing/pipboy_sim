package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the vault_dweller database table.
 * 
 */
@Entity
@Table(name="vault_dweller")
@NamedQuery(name="VaultDweller.findAll", query="SELECT v FROM VaultDweller v")
public class VaultDweller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dweller_id")
	private int dwellerId;
    
	private String gender;

	private int intelligence;

	@Temporal(TemporalType.DATE)
	@Column(name="join_date", nullable = false)
	private Date joinDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_seen")
	private Date lastSeen;
	
	@PrePersist
    protected void onCreate() {
        if (joinDate == null) {
            joinDate = new Date();
        }
        if (lastSeen == null) {
            lastSeen = new Date();
        }
    }
	
	private String name;

	@Column(name="radiation_level")
	private BigDecimal radiationLevel;

	private String status;

	private int strength;

	//bi-directional one-to-one association to DwellerLocation
	@OneToOne(mappedBy="vaultDweller")
	private DwellerLocation dwellerLocation;

	//bi-directional many-to-one association to DwellerQuest
	@OneToMany(mappedBy="vaultDweller")
	private List<DwellerQuest> dwellerQuests;

	//bi-directional many-to-one association to InventoryItem
	@OneToMany(mappedBy="vaultDweller")
	private List<InventoryItem> inventoryItems;

	//bi-directional many-to-one association to Vault
	@ManyToOne
	@JoinColumn(name="vault_number")
	private Vault vault;

	@Column(name="username", unique=true, nullable=false)
    private String username;

    @Column(name="password_hash", nullable=false)
    private String passwordHash;

    @Column(name="security_clearance", nullable=false)
    private String securityClearance; // "OVERSEER", "SECURITY", "SCIENTIST", "DWELLER"
    
	public VaultDweller() {
	}

	public int getDwellerId() {
		return this.dwellerId;
	}

	public void setDwellerId(int dwellerId) {
		this.dwellerId = dwellerId;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getIntelligence() {
		return this.intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLastSeen() {
		return this.lastSeen;
	}

	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getRadiationLevel() {
		return this.radiationLevel;
	}

	public void setRadiationLevel(BigDecimal radiationLevel) {
		this.radiationLevel = radiationLevel;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStrength() {
		return this.strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public DwellerLocation getDwellerLocation() {
		return this.dwellerLocation;
	}

	public void setDwellerLocation(DwellerLocation dwellerLocation) {
		this.dwellerLocation = dwellerLocation;
	}

	public List<DwellerQuest> getDwellerQuests() {
		return this.dwellerQuests;
	}

	public void setDwellerQuests(List<DwellerQuest> dwellerQuests) {
		this.dwellerQuests = dwellerQuests;
	}

	public DwellerQuest addDwellerQuest(DwellerQuest dwellerQuest) {
		getDwellerQuests().add(dwellerQuest);
		dwellerQuest.setVaultDweller(this);

		return dwellerQuest;
	}

	public DwellerQuest removeDwellerQuest(DwellerQuest dwellerQuest) {
		getDwellerQuests().remove(dwellerQuest);
		dwellerQuest.setVaultDweller(null);

		return dwellerQuest;
	}

	public List<InventoryItem> getInventoryItems() {
		return this.inventoryItems;
	}

	public void setInventoryItems(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public InventoryItem addInventoryItem(InventoryItem inventoryItem) {
		getInventoryItems().add(inventoryItem);
		inventoryItem.setVaultDweller(this);

		return inventoryItem;
	}

	public InventoryItem removeInventoryItem(InventoryItem inventoryItem) {
		getInventoryItems().remove(inventoryItem);
		inventoryItem.setVaultDweller(null);

		return inventoryItem;
	}

	public Vault getVault() {
		return this.vault;
	}

	public void setVault(Vault vault) {
		this.vault = vault;
	}
	
	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSecurityClearance() {
        return securityClearance;
    }

    public void setSecurityClearance(String securityClearance) {
        this.securityClearance = securityClearance;
    }

}