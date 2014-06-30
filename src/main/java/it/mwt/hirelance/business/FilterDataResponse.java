package it.mwt.hirelance.business;

import it.mwt.hirelance.business.model.Category;
import it.mwt.hirelance.business.model.Skill;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class FilterDataResponse<R> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalItems;
	private List<R> items;
	
	///////////// PER IL PROJECT FORM: ////////////////
	private Collection<Category> subCategories;
	private Collection<Skill> skills;
	private Collection<Skill> preSelectedSkills;
	
	public FilterDataResponse() {
		// TODO Auto-generated constructor stub
	}
	
	
	public FilterDataResponse(Collection<Category> subCategories,
			Collection<Skill> skills, Collection<Skill> preSelectedSkills) {
		super();
		this.subCategories = subCategories;
		this.skills = skills;
		this.preSelectedSkills = preSelectedSkills;
	}

	public FilterDataResponse(int totalItems, List<R> items) {
		super();
		this.totalItems = totalItems;
		this.items = items;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public List<R> getItems() {
		return items;
	}

	public void setItems(List<R> items) {
		this.items = items;
	}

	public Collection<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Collection<Category> subCategories) {
		this.subCategories = subCategories;
	}

	public Collection<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Collection<Skill> skills) {
		this.skills = skills;
	}

	public Collection<Skill> getPreSelectedSkills() {
		return preSelectedSkills;
	}

	public void setPreSelectedSkills(Collection<Skill> preSelectedSkills) {
		this.preSelectedSkills = preSelectedSkills;
	}

	
}
