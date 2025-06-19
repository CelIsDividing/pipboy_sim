package model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the dweller_quest database table.
 * 
 */
@Embeddable
public class DwellerQuestPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="dweller_id", insertable=false, updatable=false)
	private int dwellerId;

	@Column(name="quest_id", insertable=false, updatable=false)
	private int questId;

	public DwellerQuestPK() {
	}
	public int getDwellerId() {
		return this.dwellerId;
	}
	public void setDwellerId(int dwellerId) {
		this.dwellerId = dwellerId;
	}
	public int getQuestId() {
		return this.questId;
	}
	public void setQuestId(int questId) {
		this.questId = questId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DwellerQuestPK)) {
			return false;
		}
		DwellerQuestPK castOther = (DwellerQuestPK)other;
		return 
			(this.dwellerId == castOther.dwellerId)
			&& (this.questId == castOther.questId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.dwellerId;
		hash = hash * prime + this.questId;
		
		return hash;
	}
}