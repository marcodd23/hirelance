package it.mwt.hirelance.business.impl.jpa;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.ImageService;
import it.mwt.hirelance.business.PortfolioService;
import it.mwt.hirelance.business.model.FreelanceProfile;
import it.mwt.hirelance.business.model.PortfolioItem;
import it.mwt.hirelance.business.model.User;

@Service
public class JPAPortfolioService implements PortfolioService {
	
	@Autowired
	private ImageService uploadService;
	
	@Value("#{uploadPaths.portfolioItemImagePath}")
	private String portfolioItemImagePath;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Collection<PortfolioItem> findAllPortfolioItems(int freelanceID) throws BusinessException {
		FreelanceProfile fp= em.find(FreelanceProfile.class, freelanceID);
		return fp.getPortfolio();
	}

	@Override
	@Transactional
	public void create(PortfolioItem portfolioItem,User user) throws BusinessException {
		if(portfolioItem.getPortfolioFile() != null){
		      uploadService.saveProfileImage(portfolioItem.getPortfolioFile(), user.getUserID(), portfolioItemImagePath);
		}
		//System.out.println("Dimensione prima insert: "+user.getFreelanceProfile().getPortfolio().size());
		//user.getFreelanceProfile().addPortfolioItem(portfolioItem);
		//em.merge(user.getFreelanceProfile());
		em.persist(portfolioItem);
		//System.out.println("Dimensione dopo insert: "+user.getFreelanceProfile().getPortfolio().size());
		user.getFreelanceProfile().addPortfolioItem(portfolioItem);
		//System.out.println("Dimensione dopo add e prima merge: "+user.getFreelanceProfile().getPortfolio().size());
		em.merge(user.getFreelanceProfile());
		//System.out.println("Dimensione dopo merge: "+user.getFreelanceProfile().getPortfolio().size());

		//em.refresh(user.getFreelanceProfile());
		//em.merge(user.getFreelanceProfile());
		
	}

	@Override
	public List<PortfolioItem> findMorePortfolioItems(int freelanceID) throws BusinessException {
		TypedQuery<PortfolioItem> query = em.createQuery("SELECT f.portfolio FROM FreelanceProfile f WHERE f.freelanceID = "+freelanceID, PortfolioItem.class);
		return query.setFirstResult(3).getResultList();
	}

	@Override
	@Transactional
	public void deletePortfolioItem(int portfolioItemID,User u) throws BusinessException {
		PortfolioItem p = em.find(PortfolioItem.class, portfolioItemID);
		em.remove(em.merge(p));
		//System.out.println("Dimensione prima: "+u.getFreelanceProfile().getPortfolio().size());
		for (Iterator<PortfolioItem> i=u.getFreelanceProfile().getPortfolio().iterator();i.hasNext();) {
			PortfolioItem x = i.next();
			//System.out.print(x.getPortfolioItemID()+"=");
			//System.out.println(portfolioItemID+"?");
			if(x.getPortfolioItemID()==portfolioItemID){
				//System.out.println("Sono uguali");
				i.remove();
			}
		}
		em.getEntityManagerFactory().getCache().evictAll();
		//System.out.println("Dimensione dopo: "+u.getFreelanceProfile().getPortfolio().size());
		em.merge(u.getFreelanceProfile());
		//System.out.println("Dimensione dopo merge: "+u.getFreelanceProfile().getPortfolio().size());

		//em.refresh(u.getFreelanceProfile());
	}

}
