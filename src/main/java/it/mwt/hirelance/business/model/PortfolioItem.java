package it.mwt.hirelance.business.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PortfolioItem
 *
 */
@Entity

@Table(name = "PORTFOLIO_ITEMS")
public class PortfolioItem implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4348158834258687290L;
	private int portfolioItemID;
	private String title;
	private String description;
	private UploadedFile portfolioFile;

	public PortfolioItem() {
		super();
	} 
	
	public PortfolioItem(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	@Id    
	@Column(name = "PORTFOLIOITEM_ID")
	@GeneratedValue(generator = "PortfolioItem_Gen")
	@SequenceGenerator(name = "PortfolioItem_Gen", sequenceName = "PortfolioItem_Seq", initialValue=1, allocationSize=1)
	public int getPortfolioItemID() {
		return this.portfolioItemID;
	}

	public void setPortfolioItemID(int portfolioItemID) {
		this.portfolioItemID = portfolioItemID;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval=true)
	@JoinColumn(name="IMAGE_ID", nullable = true)
	public UploadedFile getPortfolioFile() {
		return portfolioFile;
	}

	public void setPortfolioFile(UploadedFile portfolioFile) {
		this.portfolioFile = portfolioFile;
	}  
	
	
}
