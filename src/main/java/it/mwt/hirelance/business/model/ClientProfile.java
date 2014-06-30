package it.mwt.hirelance.business.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static javax.persistence.FetchType.EAGER;


/**
 * Entity implementation class for Entity: ClientProfile
 *
 */
@Entity
@Table(name = "CLIENT_PROFILES")
public class ClientProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2227780708994519543L;
	private int clientID;
	private String clientName;
	private String companyName;
	private String jobTitle;
	private String description;
	private String videoLink;
	private User user;
	private UploadedFile image;
	private Collection<Project> projects = new ArrayList<Project>();
	//private Collection<WorkRoom> workRooms = new HashSet<WorkRoom>();
	private Collection<FreelanceProfile> preferredFreelances = new HashSet<FreelanceProfile>();
	private float rating;
	private int totalProjects;

	public ClientProfile() {
		super();
	}
	
	public ClientProfile(String clientName) {
		super();
		this.clientName = clientName;
	}

	@Id
	@Column(name = "CLIENT_ID")
	@GeneratedValue(generator = "Client_Gen")
	@SequenceGenerator(name = "Client_Gen", sequenceName = "Client_Seq",initialValue=1, allocationSize=1)
	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
	
	@JsonIgnore
    @OneToOne(mappedBy = "clientProfile" )
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval=true)
	@JoinColumn(name="IMAGE_ID", nullable = true)
	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.REMOVE}, mappedBy = "clientOwner",orphanRemoval=true)
	@JsonIgnore
	public Collection<Project> getProjects() {
		return projects;
	}

	public void setProjects(Collection<Project> projects) {
		this.projects = projects;
	}
	
    @ManyToMany(fetch=FetchType.EAGER, mappedBy = "preferredClients")
	public Collection<FreelanceProfile> getPreferredFreelances() {
		return preferredFreelances;
	}

	public void setPreferredFreelances(
			Collection<FreelanceProfile> preferredFreelances) {
		this.preferredFreelances = preferredFreelances;
	}
   
	public void addProject(Project project){
		this.projects.add(project);
		if(project.getClientOwner() == null){
		    project.setClientOwner(this);
		}
	}
	
	public void addPreferredFreelance(FreelanceProfile preferredFreelance){
		this.preferredFreelances.add(preferredFreelance);
	}
	
	public int getTotalProjects() {
		return totalProjects;
	}

	public void setTotalProjects(int totalProjects) {
		this.totalProjects = totalProjects;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
}
