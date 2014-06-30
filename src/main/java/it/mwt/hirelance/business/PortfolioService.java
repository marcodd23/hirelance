package it.mwt.hirelance.business;

import it.mwt.hirelance.business.model.PortfolioItem;
import it.mwt.hirelance.business.model.User;

import java.util.Collection;
import java.util.List;

public interface PortfolioService {

	Collection<PortfolioItem> findAllPortfolioItems(int freelanceID) throws BusinessException;
	void create(PortfolioItem portfolioItem, User user) throws BusinessException;
	List<PortfolioItem> findMorePortfolioItems(int freelanceID) throws BusinessException;
	void deletePortfolioItem(int portfolioItemID, User u) throws BusinessException;

}
