package it.mwt.hirelance.business.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import static javax.persistence.TemporalType.TIMESTAMP;


/**
 * Entity implementation class for Entity: Curriculum
 *
 */
@Entity
@Table(name = "CURRICULUMS")
public class Curriculum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5666422184282682080L;
	private int curriculumID;
	private String aboutMe;
	private Date lastUpdate;
	private Collection<Education> educations = new ArrayList<Education>();
	private Collection<Employment> employments = new ArrayList<Employment>();
	private Collection<Language> languages = new ArrayList<Language>();
	private Collection<Skill> cvSkills = new ArrayList<Skill>(); 

	public Curriculum() {
		super();
	}
	
    public Curriculum(String aboutMe, Date lastUpdate) {
		super();
		this.aboutMe = aboutMe;
		this.lastUpdate = lastUpdate;
	}

	@Id
	@Column(name = "CURRICULUM_ID")
	@GeneratedValue(generator = "Curriculum_Gen")
	@SequenceGenerator(name = "Curriculum_Gen", sequenceName = "Curriculum_Seq", initialValue=1, allocationSize=1)
	public int getCurriculumID() {
		return curriculumID;
	}

	public void setCurriculumID(int curriculumID) {
		this.curriculumID = curriculumID;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	@Temporal(TIMESTAMP)
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@OneToMany(fetch=FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "CURRICULUM_FK", referencedColumnName = "CURRICULUM_ID")
	public Collection<Education> getEducations() {
		return educations;
	}

	public void setEducations(Collection<Education> educations) {
		this.educations = educations;
	}

	@OneToMany(fetch=FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "CURRICULUM_FK", referencedColumnName = "CURRICULUM_ID")
	public Collection<Employment> getEmployments() {
		return employments;
	}

	public void setEmployments(Collection<Employment> employments) {
		this.employments = employments;
	}

	@OneToMany(fetch=FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "CURRICULUM_FK", referencedColumnName = "CURRICULUM_ID")
	public Collection<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(Collection<Language> languages) {
		this.languages = languages;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "curriculum_skills", joinColumns= @JoinColumn(name = "curriculum_fk"), inverseJoinColumns= @JoinColumn(name = "skill_fk") )
	public Collection<Skill> getCvSkills() {
		return cvSkills;
	}

	public void setCvSkills(Collection<Skill> cvSkills) {
		this.cvSkills = cvSkills;
	}
	
	public void addEducation(Education education){
		this.educations.add(education);
	}
	
	public void addEmployment(Employment employment){
		this.employments.add(employment);
	}
	
	public void addLanguage(Language language){
		this.languages.add(language);
	}
	
	public void addCvSkill(Skill cvSkill){
		this.cvSkills.add(cvSkill);
	}
   
}