package it.mwt.hirelance.business.impl.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.SecurityService;
import it.mwt.hirelance.business.model.User;

@Service
public class JPASecurityService implements SecurityService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User authenticate(String username) throws BusinessException {
		//System.out.println("Sono in JPASecurityService con username "+ username);
		String select = "SELECT u FROM User u WHERE u.username LIKE '"+username+"'";
		TypedQuery<User> query = em.createQuery(select, User.class);
		
		return query.getResultList().get(0);
		
	}

}
