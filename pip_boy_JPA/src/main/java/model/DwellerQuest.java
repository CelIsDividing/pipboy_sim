package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the dweller_quest database table.
 * 
 */
@Entity
@Table(name="dweller_quest")
@NamedQuery(name="DwellerQuest.findAll", query="SELECT d FROM DwellerQuest d")
public class DwellerQuest implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DwellerQuestPK id;

	private int progress;

	//bi-directional many-to-one association to Quest
	@ManyToOne
	@JoinColumn(name="quest_id")
	private Quest quest;

	//bi-directional many-to-one association to VaultDweller
	@ManyToOne
	@JoinColumn(name="dweller_id")
	private VaultDweller vaultDweller;

	public DwellerQuest() {
	}

	public DwellerQuestPK getId() {
		return this.id;
	}

	public void setId(DwellerQuestPK id) {
		this.id = id;
	}

	public int getProgress() {
		return this.progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public Quest getQuest() {
		return this.quest;
	}

	public void setQuest(Quest quest) {
		this.quest = quest;
	}

	public VaultDweller getVaultDweller() {
		return this.vaultDweller;
	}

	public void setVaultDweller(VaultDweller vaultDweller) {
		this.vaultDweller = vaultDweller;
	}

}