package it.mwt.hirelance.business.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Project
 *
 */
@Entity

@Table(name = "PROJECTS")
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4385777002696131580L;
	private int projectID;
	private String title;
	private String description;
	private ProjectStatus status;
	private Category projectSubCategory;
	private ClientProfile clientOwner;
	private Collection<Skill> skills = new HashSet<Skill>();
	private BigDecimal budgetMIN;
	private BigDecimal budgetMAX;
	private Date postedDate;
	private Date expiryDate;
	private String country;
	private int totalProposal;
	private String timeLeft;
	private boolean valuated;
	
	

	public Project() {
		super();
	}

	public Project(int projectID, String title, String description,
			ProjectStatus status, Category projectSubCategory,
			ClientProfile clientOwner,Collection<Skill> skills, BigDecimal budgetMIN,
			BigDecimal budgetMAX, Date postedDate, Date expiryDate,
			String country, int totalProposal) {
		super();
		this.projectID = projectID;
		this.title = title;
		this.description = description;
		this.status = status;
		this.projectSubCategory = projectSubCategory;
		this.clientOwner = clientOwner;
		this.skills = skills;
		this.budgetMIN = budgetMIN;
		this.budgetMAX = budgetMAX;
		this.postedDate = postedDate;
		this.expiryDate = expiryDate;
		this.country = country;
		this.totalProposal= totalProposal;
	}

	@Id
	@Column(name = "PROJECT_ID")
	@GeneratedValue(generator = "Project_Gen")
	@SequenceGenerator(name = "Project_Gen", sequenceName = "Project_Seq", initialValue=1, allocationSize=1)
	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Enumerated(EnumType.STRING)
	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUBCATEGORY_FK", referencedColumnName = "CATEGORY_ID")
	public Category getProjectSubCategory() {
		return projectSubCategory;
	}

	public void setProjectSubCategory(Category projectSubCategory) {
		this.projectSubCategory = projectSubCategory;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "CLIENT_FK", referencedColumnName = "CLIENT_ID")
	public ClientProfile getClientOwner() {
		return clientOwner;
	}

	public void setClientOwner(ClientProfile clientOwner) {
		this.clientOwner = clientOwner;
/*		if(!clientOwner.getProjects().contains(this)){
			clientOwner.getProjects().add(this);
		}*/
	}

	public BigDecimal getBudgetMIN() {
		return budgetMIN;
	}

	public void setBudgetMIN(BigDecimal budgetMIN) {
		this.budgetMIN = budgetMIN;
	}

	public BigDecimal getBudgetMAX() {
		return budgetMAX;
	}

	public void setBudgetMAX(BigDecimal budgetMAX) {
		this.budgetMAX = budgetMAX;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "Project_Skill", joinColumns = @JoinColumn(name = "PROJECT_FK", referencedColumnName = "PROJECT_ID"), inverseJoinColumns = @JoinColumn(name = "SKILL_FK", referencedColumnName = "SKILL_ID"))
	public Collection<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Collection<Skill> skills) {
		this.skills = skills;
	}
   
	public void addSkill(Skill skill){
		this.skills.add(skill);
	}
	
	public int getTotalProposal() {
		return totalProposal;
	}

	public void setTotalProposal(int totalProposal) {
		this.totalProposal = totalProposal;
	}
	
	@Transient
	public String getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(String timeLeft) {
		this.timeLeft = timeLeft;
	}



	public boolean isValuated() {
		return valuated;
	}

	public void setValuated(boolean valuated) {
		this.valuated = valuated;
	}



	public enum ProjectStatus{
		HIRING_OPEN("Hiring OPEN"), 
		HIRING_CLOSED("Hiring CLOSED"),
		EXPIRED("EXPIRED");
		
		private final String status;

		private ProjectStatus(final String status) {
			this.status = status;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return status;
		}	
	}

}
