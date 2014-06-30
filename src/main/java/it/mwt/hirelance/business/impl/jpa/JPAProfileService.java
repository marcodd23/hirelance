package it.mwt.hirelance.business.impl.jpa;
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
import it.mwt.hirelance.business.FilterDataRequest;
import it.mwt.hirelance.business.FilterDataResponse;
import it.mwt.hirelance.business.ImageService;
import it.mwt.hirelance.business.ProfileService;
import it.mwt.hirelance.business.model.ClientProfile;
import it.mwt.hirelance.business.model.FreelanceProfile;
import it.mwt.hirelance.business.model.Proposal;
import it.mwt.hirelance.business.model.UploadedFile;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.business.model.Project;

@Service
public class JPAProfileService implements ProfileService {


	@Autowired
	private ImageService uploadService;
	
	@Value("#{uploadPaths.clientImagePath}")
	private String clientImagePath;
	
	@Value("#{uploadPaths.freelancerImagePath}")
	private String freelancerImagePath;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public User createFreelancerProfile(FreelanceProfile fp, User user) throws BusinessException {
		if(fp.getImage() != null){
		      uploadService.saveProfileImage(fp.getImage(), user.getUserID(), freelancerImagePath);
		   }
/*		em.persist(fp);
		user.setFreelanceProfile(fp);
		em.merge(user);*/
		
		user.setFreelanceProfile(fp);
		return em.merge(user);
	}


	@Override
	@Transactional
	public void updateFreelancerProfile(FreelanceProfile fp, User user,
			UploadedFile image) throws BusinessException {
		if(image != null){	
		     if(user.getFreelanceProfile().getImage() == null){
			       System.out.println(" =================NON AVEVO UN IMMAGINE, LA CARICO PER LA PRIMA VOLTA================= ");
			       fp.setImage(image);
			       uploadService.saveProfileImage(fp.getImage(), user.getUserID(), freelancerImagePath);
			       
		     }else{
			       System.out.println(" ==================AVEVO UN'IMMAGINE , MA LA MODIFICO===================");
			       image.setFileID(user.getFreelanceProfile().getImage().getFileID());
			       fp.setImage(image);
			       uploadService.updateProfileImage(fp.getImage(), user.getFreelanceProfile().getImage());
		     }
            
	   	}else if(user.getFreelanceProfile().getImage() != null){
			System.out.println(" ============NON CARICO IMMAGINI , MA AVEVO GIA' UN'IMMAGINE==================== ");
			fp.setImage(user.getFreelanceProfile().getImage());
	   	}
		user.setFreelanceProfile(fp);
		em.merge(user);
	}


	@Override
	@Transactional
	public void deleteFreelanceProfile(FreelanceProfile fp) throws BusinessException{
		em.remove(em.merge(fp));
	}


	@Override
	@Transactional
	public User createClientProfile(ClientProfile cp, User user) throws BusinessException {
		if(cp.getImage() != null){
			uploadService.saveProfileImage(cp.getImage(), user.getUserID(), clientImagePath);
		   }
/*		em.persist(cp);
		user.setClientProfile(cp);
		em.merge(user);*/
		
		user.setClientProfile(cp);
		return em.merge(user);
	}
	
	@Override
	@Transactional
	public void updateClientProfile(ClientProfile cp,User user, UploadedFile image) throws BusinessException {
		
		if(image != null){	
		     if(user.getClientProfile().getImage() == null){
			       System.out.println(" =================NON AVEVO UN IMMAGINE, LA CARICO PER LA PRIMA VOLTA================= ");
			       cp.setImage(image);
			       uploadService.saveProfileImage(cp.getImage(), user.getUserID(), clientImagePath);
			       
		     }else{
			       System.out.println(" ==================AVEVO UN'IMMAGINE , MA LA MODIFICO===================");
			       image.setFileID(user.getClientProfile().getImage().getFileID());
			       cp.setImage(image);
			       uploadService.updateProfileImage(cp.getImage(), user.getClientProfile().getImage());
		     }
             
	   	}else if(user.getClientProfile().getImage() != null){
			System.out.println(" ============NON CARICO IMMAGINI , MA AVEVO GIA' UN'IMMAGINE==================== ");
			cp.setImage(user.getClientProfile().getImage());
	   	}
		user.setClientProfile(cp);
		em.merge(user);
		//em.merge(cp);
	}

	@Override
	public FreelanceProfile findFreelanceProfileById(int freelanceID)
			throws BusinessException {
		return em.find(FreelanceProfile.class, freelanceID);
	}

	@Override
	@Transactional
	public void addProjectToWatchList(FreelanceProfile fp, Project project) throws BusinessException {
		fp.addProjectWatchList(project);
		em.merge(fp);
		
	}

	@Transactional
	@Override
	public void removeProjectToWatchList(FreelanceProfile fp, Project project) throws BusinessException {
		for (Iterator<Project> i=fp.getProjectWatchList().iterator();i.hasNext();) {
			if(i.next().getProjectID()==project.getProjectID())i.remove();
		}
		em.merge(fp);
	}


	@Override
	public FilterDataResponse<Proposal> findAllProposalFiltered(
			FilterDataRequest filterDataRequest) throws BusinessException {
		
		int firstResult = filterDataRequest.getItemsForPage()*filterDataRequest.getPage()-filterDataRequest.getItemsForPage();
		int maxResult = filterDataRequest.getItemsForPage();
		
		TypedQuery<Proposal> query = em.createQuery("SELECT p FROM Proposal p WHERE p.status LIKE '"+filterDataRequest.getStatus()+"'" +
				" AND p.refProject.clientOwner.user.userID = "+filterDataRequest.getUserID()+
				" AND p.refProject.valuated = 1",Proposal.class);
		TypedQuery<Proposal> queryPaginated = em.createQuery("SELECT p FROM Proposal p WHERE p.status LIKE '"+filterDataRequest.getStatus()+"'" +
				" AND p.refProject.clientOwner.user.userID = "+filterDataRequest.getUserID()+
				" AND p.refProject.valuated = 1",Proposal.class);
		int totalItems=query.getResultList().size();
		List<Proposal> proposals = queryPaginated.setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
		return new FilterDataResponse<Proposal>(totalItems,proposals);
	}		
}

