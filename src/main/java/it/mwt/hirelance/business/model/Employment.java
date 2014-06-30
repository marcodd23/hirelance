package it.mwt.hirelance.business.model;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Entity implementation class for Entity: Employment
 *
 */
@Entity
@Table(name = "EMPLOYMENTS")
public class Employment implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -525665510298634303L;
	private int employmentID;
	private String company;
	private String description;
	private Date startJob;
	private Date endJob;

	public Employment() {
		super();
	}   
	
	
	public Employment(String company, String description, Date startJob, Date endJob) {
		super();
		this.company = company;
		this.description = description;
		this.startJob = startJob;
		this.endJob = endJob;
	}

	@Id    
	@Column(name = "EMPLOYMENT_ID")
	@GeneratedValue(generator = "Employment_Gen")
	@SequenceGenerator(name = "Employment_Gen", sequenceName = "Employment_Seq", initialValue=1, allocationSize=1)
	public int getEmploymentID() {
		return this.employmentID;
	}

	public void setEmploymentID(int employmentID) {
		this.employmentID = employmentID;
	}   
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	@Temporal(TIMESTAMP)
	public Date getStartJob() {
		return this.startJob;
	}

	public void setStartJob(Date startJob) {
		this.startJob = startJob;
	}   
	@Temporal(TIMESTAMP)
	public Date getEndJob() {
		return this.endJob;
	}

	public void setEndJob(Date endJob) {
		this.endJob = endJob;
	}
	
}
