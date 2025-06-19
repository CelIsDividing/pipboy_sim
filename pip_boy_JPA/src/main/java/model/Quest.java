package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the quest database table.
 * 
 */
@Entity
@NamedQuery(name="Quest.findAll", query="SELECT q FROM Quest q")
public class Quest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="quest_id")
	private int questId;

	@Lob
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="reward_caps")
	private int rewardCaps;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	private String status;

	private String title;

	//bi-directional many-to-one association to DwellerQuest
	@OneToMany(mappedBy="quest")
	private List<DwellerQuest> dwellerQuests;

	public Quest() {
	}

	public int getQuestId() {
		return this.questId;
	}

	public void setQuestId(int questId) {
		this.questId = questId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getRewardCaps() {
		return this.rewardCaps;
	}

	public void setRewardCaps(int rewardCaps) {
		this.rewardCaps = rewardCaps;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<DwellerQuest> getDwellerQuests() {
		return this.dwellerQuests;
	}

	public void setDwellerQuests(List<DwellerQuest> dwellerQuests) {
		this.dwellerQuests = dwellerQuests;
	}

	public DwellerQuest addDwellerQuest(DwellerQuest dwellerQuest) {
		getDwellerQuests().add(dwellerQuest);
		dwellerQuest.setQuest(this);

		return dwellerQuest;
	}

	public DwellerQuest removeDwellerQuest(DwellerQuest dwellerQuest) {
		getDwellerQuests().remove(dwellerQuest);
		dwellerQuest.setQuest(null);

		return dwellerQuest;
	}

}