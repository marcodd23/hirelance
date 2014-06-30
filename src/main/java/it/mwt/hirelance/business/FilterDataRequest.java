package it.mwt.hirelance.business;

import java.io.Serializable;

import it.mwt.hirelance.business.model.Skill;

public class FilterDataRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String category;
	private Skill skill;
	private String search;
	private String itemSort;
	private String dirSort;
	private int itemsForPage;
	private int page;
	private int userID;
	private int projectID;
	private int freelanceID;
	private int proposalID;
	private String status;
	private String conversationType;
	//private int workRoomID;
	
	public FilterDataRequest() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getItemSort() {
		return itemSort;
	}

	public void setItemSort(String itemSort) {
		this.itemSort = itemSort;
	}

	public String getDirSort() {
		return dirSort;
	}

	public void setDirSort(String dirSort) {
		this.dirSort = dirSort;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public int getItemsForPage() {
		return itemsForPage;
	}

	public void setItemsForPage(int itemsForPage) {
		this.itemsForPage = itemsForPage;
	}

	public boolean isEmpty(){
		if(this.category==null&&this.itemSort==null&&this.skill==null&&this.search==null&&this.page==0){
			return true;
		}
		return false;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public int getFreelanceID() {
		return freelanceID;
	}

	public void setFreelanceID(int freelanceID) {
		this.freelanceID = freelanceID;
	}

	public int getProposalID() {
		return proposalID;
	}

	public void setProposalID(int proposalID) {
		this.proposalID = proposalID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConversationType() {
		return conversationType;
	}

	public void setConversationType(String conversationType) {
		this.conversationType = conversationType;
	}

		
	
}
