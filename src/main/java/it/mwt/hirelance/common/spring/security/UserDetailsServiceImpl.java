package it.mwt.hirelance.common.spring.security;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.SecurityService;
import it.mwt.hirelance.business.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SecurityService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user;
		try {
			//System.out.println("Sono in UserDetailsServiceImpl con username "+username);
			user = service.authenticate(username);
		} catch (BusinessException e) {
			throw new UsernameNotFoundException("utente non trovato");
		}

		if (user==null) {
			throw new UsernameNotFoundException("utente non trovato");
		}
		//System.out.println("Torno in UserDetailsServiceImpl "+user.toString());
		return new UserDetailsImpl(user);
	}

}
