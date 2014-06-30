package it.mwt.hirelance.business.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.MERGE;

/**
 * Entity implementation class for Entity: FeedBack
 *
 */
@Entity
@Table(name = "FEEDBACK")
public class FeedBack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4272613481933551862L;
	private int feedBackID;
	private User beneficiary;
	private int grade;
	private String remark;
	private String type; // freelancer / client
	private Proposal jobEvaluated;

	public FeedBack() {
		super();
	}

	public FeedBack(User beneficiary, int grade, String remark,
			String type, Proposal jobEvaluated) {
		super();
		this.beneficiary = beneficiary;
		this.grade = grade;
		this.remark = remark;
		this.type = type;
		this.jobEvaluated = jobEvaluated;
	}

	@Id
	@Column(name = "FEEDBACK_ID")
	@GeneratedValue(generator = "FeedBack_Gen")
	@SequenceGenerator(name = "FeedBack_Gen", sequenceName = "FeedBack_Seq", initialValue=1, allocationSize=1)
	public int getFeedBackID() {
		return feedBackID;
	}

	public void setFeedBackID(int feedBackID) {
		this.feedBackID = feedBackID;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "USER_FK", referencedColumnName = "USER_ID")
	public User getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(User beneficiary) {
		this.beneficiary = beneficiary;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "PROPOSAL_FK", referencedColumnName = "PROPOSAL_ID")
	@JsonIgnore
	public Proposal getJobEvaluated() {
		return jobEvaluated;
	}

	public void setJobEvaluated(Proposal jobEvaluated) {
		this.jobEvaluated = jobEvaluated;
	}
    
}
