package it.mwt.hirelance.common.spring.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String role;

	public GrantedAuthorityImpl(String role) {
		super();
		this.role = role;
	}


	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role;
	}

	@Override
	public String toString() {
		return "[autority=" + role + "]";
	}
	
	

}
