package it.mwt.hirelance.business.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Entity implementation class for Entity: Proposal
 *
 */
@Entity
@Table(name = "PROPOSALS")
public class Proposal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8065469393118241716L;
	private int proposalID;
	private String description;
	private Date proposalDate;
	private BigDecimal payBid;
	private Integer deliveryTime;
	private Project refProject;
	private FreelanceProfile aspirantFreelance;
	private Collection<FeedBack> evaluations = new ArrayList<FeedBack>();
	private ProposalStatus status;
	private int newMessageClientCounter;
	private int newMessageFreelanceCounter;
    private Date jobStartDate;
    private Date jobFinishedDate;

	public Proposal() {
		super();
		//this.newMessageCounter = 0;
	}

	@Id
	@Column(name = "PROPOSAL_ID")
	@GeneratedValue(generator = "Proposal_Gen")
	@SequenceGenerator(name = "Proposal_Gen", sequenceName = "Proposal_Seq", initialValue=1, allocationSize=1)
	public int getProposalID() {
		return proposalID;
	}

	public void setProposalID(int proposalID) {
		this.proposalID = proposalID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TIMESTAMP)
	public Date getProposalDate() {
		return proposalDate;
	}

	public void setProposalDate(Date proposalDate) {
		this.proposalDate = proposalDate;
	}

	
	@Temporal(TIMESTAMP)
	public Date getJobStartDate() {
		return jobStartDate;
	}

	public void setJobStartDate(Date jobStartDate) {
		this.jobStartDate = jobStartDate;
	}

	@Temporal(TIMESTAMP)
	public Date getJobFinishedDate() {
		return jobFinishedDate;
	}

	public void setJobFinishedDate(Date jobFinishedDate) {
		this.jobFinishedDate = jobFinishedDate;
	}

	public BigDecimal getPayBid() {
		return payBid;
	}

	public void setPayBid(BigDecimal payBid) {
		this.payBid = payBid;
	}

	public Integer getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	@ManyToOne
	@JoinColumn(name = "PROJECT_FK", referencedColumnName = "PROJECT_ID")
	public Project getRefProject() {
		return refProject;
	}

	public void setRefProject(Project refProject) {
		this.refProject = refProject;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FREELANCE_FK", referencedColumnName = "FREELANCE_ID")
	public FreelanceProfile getAspirantFreelance() {
		return aspirantFreelance;
	}

	public void setAspirantFreelance(FreelanceProfile aspirantFreelance) {
		this.aspirantFreelance = aspirantFreelance;
	}	
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "jobEvaluated")
	public Collection<FeedBack> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(Collection<FeedBack> evaluations) {
		this.evaluations = evaluations;
	}

	@Enumerated(EnumType.STRING)
	public ProposalStatus getStatus() {
		return status;
	}

	public void setStatus(ProposalStatus status) {
		this.status = status;
	}
	
	public int getNewMessageClientCounter() {
		return newMessageClientCounter;
	}

	public void setNewMessageClientCounter(int newMessageClientCounter) {
		this.newMessageClientCounter = newMessageClientCounter;
	}

	public int getNewMessageFreelanceCounter() {
		return newMessageFreelanceCounter;
	}

	public void setNewMessageFreelanceCounter(int newMessageFreelanceCounter) {
		this.newMessageFreelanceCounter = newMessageFreelanceCounter;
	}

	public void incrementNewMessageClientCounter(){

		this.newMessageClientCounter += 1;
	}
	
	public void resetNewMessageClientCounter(){
		this.newMessageClientCounter = 0;
	}
	
	public void incrementNewMessageFreelanceCounter(){
		//if(this.newMessageCounter == 0){
		this.newMessageFreelanceCounter += 1;
	/*	}else{
			this.newMessageCounter = 0;
		}*/
	}
	
	public void resetNewMessageFreelanceCounter(){
		this.newMessageFreelanceCounter = 0;
	}


	public enum ProposalStatus{
		
		NOT_HIRED("Not Selected"),
		WORKING("Project Started and Working on"),
		COMPLETED("Project Completed");
		
		private final String status;

		private ProposalStatus(final String status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return status;
		}	
	}
	

}