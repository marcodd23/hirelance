package it.mwt.hirelance.business.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Role
 *
 */
@Entity

@Table(name = "ROLES")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1500101659441888215L;
	private int roleID;
	private String name;

	public Role() {
		super();
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	@Id
	@Column(name = "ROLE_ID")
	@GeneratedValue(generator = "Role_Gen")
	@SequenceGenerator(name = "Role_Gen", sequenceName = "Role_Seq", initialValue=1, allocationSize=1)
	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
