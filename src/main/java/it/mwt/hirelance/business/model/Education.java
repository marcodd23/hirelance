package it.mwt.hirelance.business.model;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Education
 *
 */
@Entity
@Table(name = "EDUCATIONS")
public class Education implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2604171149886028079L;
	private int educationID;
	private String institute;
	private String location;
	private int grade;
	private Date graduationDate;
	private String subjects;

	public Education() {
		super();
	}  
	
	
	public Education(String institute, String location, int grade,
			Date graduationDate, String subjects) {
		super();
		this.institute = institute;
		this.location = location;
		this.grade = grade;
		this.graduationDate = graduationDate;
		this.subjects = subjects;
	}


	@Id
	@Column(name = "EDUCATION_ID")
	@GeneratedValue(generator = "Education_Gen")
	@SequenceGenerator(name = "Education_Gen", sequenceName = "Education_Seq", initialValue=1, allocationSize=1)
	public int getEducationID() {
		return this.educationID;
	}

	public void setEducationID(int educationID) {
		this.educationID = educationID;
	}   
	public String getInstitute() {
		return this.institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}   
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}   
	public int getGrade() {
		return this.grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}   
	@Temporal(TemporalType.DATE)
	public Date getGraduationDate() {
		return this.graduationDate;
	}

	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}   
	public String getSubjects() {
		return this.subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
   
}
