package it.mwt.hirelance.business.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Entity implementation class for Entity: ClientProfile
 *
 */
@Entity
@Table(name = "CATEGORIES")
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5829419830722998153L;
	private int catID;
	private String name;
	private Collection<Skill> skills = new HashSet<Skill>();
	private Category parentCategory;
	
	
	public Category() {
		super();
	}

	@Id
	@GeneratedValue(generator = "Category_Gen")
	@SequenceGenerator(name = "Category_Gen", sequenceName = "Category_Seq", initialValue=1, allocationSize=1)
	@Column(name = "CATEGORY_ID")
	public int getCatID() {
		return catID;
	}

	public void setCatID(int catID) {
		this.catID = catID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch=FetchType.EAGER, mappedBy="mainCategory", orphanRemoval = true)
	@JsonIgnore
	public Collection<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Collection<Skill> skills) {
		this.skills = skills;
	}

	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PARENTCATEGORY_FK")
	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}


	
}
