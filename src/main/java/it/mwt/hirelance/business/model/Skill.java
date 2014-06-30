package it.mwt.hirelance.business.model;


import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Skill
 *
 */
@Entity
@Table(name = "SKILLS")
public class Skill implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9128341100406469264L;
	private int skillID;
	private String name;
	private String description;
	private Category mainCategory;

	public Skill() {
		super();
	} 
	
	public Skill(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	@Id    
	@Column(name = "SKILL_ID")
	@GeneratedValue(generator = "Skill_Gen")
	@SequenceGenerator(name = "Skill_Gen", sequenceName = "Skill_Seq", initialValue=1, allocationSize=1)
	public int getSkillID() {
		return this.skillID;
	}

	public void setSkillID(int skillID) {
		this.skillID = skillID;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MAINCATEGORY_FK")
	public Category getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(Category mainCategory) {
		this.mainCategory = mainCategory;
	}
   
}
