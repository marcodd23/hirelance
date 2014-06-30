package it.mwt.hirelance.business.model;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

@Table(name = "USERS")
public class User implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7584249497181510912L;
	private int userID;
	private String username;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String phoneNumb;
	private String country;
	private String address;
	private String city;
	private String province;
	private String cap;
	private Date creationDate;
	private Role role;
	private FreelanceProfile freelanceProfile;
	private ClientProfile clientProfile;
	private int newSystemMessageCounter;

	public User() {
		super();
	}  
	
	public User(String username, String password, String name, String surname,
			String email, Date creationDate, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.creationDate = creationDate;
		this.role = role;
	}



	@GeneratedValue(generator="User_Gen") 
	@SequenceGenerator(name="User_Gen", sequenceName="User_Seq", initialValue=1, allocationSize=1)   
    @Id @Column(name="USER_ID") 
	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="FIRSTNAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumb() {
		return phoneNumb;
	}

	public void setPhoneNumb(String phoneNumb) {
		this.phoneNumb = phoneNumb;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	@Temporal(TIMESTAMP)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@ManyToOne
	@JoinColumn(name = "ROLE_FK", referencedColumnName = "ROLE_ID")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@OneToOne(fetch = FetchType.EAGER,cascade= {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
	@JoinColumn(name = "FREELANCE_FK", referencedColumnName = "FREELANCE_ID")
	public FreelanceProfile getFreelanceProfile() {
		return freelanceProfile;
	}

	public void setFreelanceProfile(FreelanceProfile freelanceProfile) {
		this.freelanceProfile = freelanceProfile;
		if(freelanceProfile!=null){
			freelanceProfile.setUser(this);
		}
	}

	
	@OneToOne(fetch = FetchType.EAGER,cascade= {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
	@JoinColumn(name = "CLIENT_FK", referencedColumnName = "CLIENT_ID")
	public ClientProfile getClientProfile() {
		return clientProfile;
	}

	public void setClientProfile(ClientProfile clientProfile) {
		this.clientProfile = clientProfile;
		if(clientProfile!=null){
			clientProfile.setUser(this);
		}
	}

	public int getNewSystemMessageCounter() {
		return newSystemMessageCounter;
	}

	public void setNewSystemMessageCounter(int newSystemMessageCounter) {
		this.newSystemMessageCounter = newSystemMessageCounter;
	}

	public void incrementNewSystemMessageCounter(){

		this.newSystemMessageCounter += 1;
	}
	
	public void decrementNewSystemMessageCounter(){
		if(newSystemMessageCounter>0){
	    	this.newSystemMessageCounter -= 1;
		}
	}
	
	@Override
	public String toString() {
		return ""+userID+"";
	}
}
