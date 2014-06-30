package it.mwt.hirelance.business;

import it.mwt.hirelance.business.model.User;

public interface SecurityService {

	User authenticate(String username) throws BusinessException;
	
}
