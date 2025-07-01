package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the inventory_item database table.
 * 
 */
@Entity
@Table(name="inventory_item")
@NamedQuery(name="InventoryItem.findAll", query="SELECT i FROM InventoryItem i")
public class InventoryItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_id")
	private int itemId;

	@Column(name="condition_percentage")
	private int conditionPercentage;

	@Column(name="item_type")
	private String itemType;

	private String name;

	private int quantity;

	private BigDecimal weight;

	//bi-directional many-to-one association to VaultDweller
	@ManyToOne
	@JoinColumn(name="dweller_id", nullable = true)
	private VaultDweller vaultDweller;

	public InventoryItem() {
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getConditionPercentage() {
		return this.conditionPercentage;
	}

	public void setConditionPercentage(int conditionPercentage) {
		this.conditionPercentage = conditionPercentage;
	}

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public VaultDweller getVaultDweller() {
		return this.vaultDweller;
	}

	public void setVaultDweller(VaultDweller vaultDweller) {
		this.vaultDweller = vaultDweller;
	}

}