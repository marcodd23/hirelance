package it.mwt.hirelance.business.impl.jpa;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.BusinessException.ExceptionCause;
import it.mwt.hirelance.business.FilterDataRequest;
import it.mwt.hirelance.business.FilterDataResponse;
import it.mwt.hirelance.business.InboxService;
import it.mwt.hirelance.business.ProfileService;
import it.mwt.hirelance.business.ProjectService;
import it.mwt.hirelance.business.RequestGrid;
import it.mwt.hirelance.business.ResponseGrid;
import it.mwt.hirelance.business.model.ClientProfile;
import it.mwt.hirelance.business.model.FeedBack;
import it.mwt.hirelance.business.model.FreelanceProfile;
import it.mwt.hirelance.business.model.Message.SystemMessageSubject;
import it.mwt.hirelance.business.model.Proposal.ProposalStatus;
import it.mwt.hirelance.business.model.Skill;
import it.mwt.hirelance.business.model.Project;
import it.mwt.hirelance.business.model.Project.ProjectStatus;
import it.mwt.hirelance.business.model.Proposal;
import it.mwt.hirelance.business.model.User;

@Service
public class JPAProjectService implements ProjectService {

	//final Logger logger = Logger.getLogger(JPAProjectService.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private InboxService inboxService;
	
	@Autowired 
	private ProfileService profileService;
	
	@Override
	@Transactional
	public void createProject(Project pr, ClientProfile cp, int daysToPost) throws BusinessException {
		Date actualDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(actualDate);
		c.add(Calendar.DATE, daysToPost);
		Date expiryDate = c.getTime();
		pr.setPostedDate(actualDate);
		pr.setExpiryDate(expiryDate);
		pr.setStatus(ProjectStatus.HIRING_OPEN);
		cp.addProject(pr);
		cp.setTotalProjects(cp.getTotalProjects()+1);
		em.persist(pr);
		em.merge(cp);		
	}


	@Override
	public Project findProjectByID(int projectID) throws BusinessException {
		Project pr = em.find(Project.class, projectID);
		if(pr!=null){
			int hoursLeft;
			int daysLeft;
			String timeLeft;
			DateTime actualDate = new DateTime(new Date());
			DateTime expiryDate = new DateTime(pr.getExpiryDate());
			int diffHours = Hours.hoursBetween(actualDate, expiryDate).getHours();
			System.out.println("============ DIFFERENZA DI ORE: " + diffHours);
			if(diffHours>0){
			  if(diffHours>=24){
				    daysLeft = Days.daysBetween(actualDate, expiryDate).getDays();
				    hoursLeft = diffHours - (daysLeft * 24);
	/*				daysLeft = diffHoursTotal / 24 ;
					hoursLeft = diffHoursTotal % 24;*/
				    timeLeft = daysLeft +"d " + hoursLeft + "h";
			     }else{
				    hoursLeft = diffHours;
				    timeLeft = hoursLeft + " hours";
			    }
			  }else{
				pr.setStatus(ProjectStatus.EXPIRED);
				//TODO
				projectUpdate(pr, 0, pr.getClientOwner());
				timeLeft = "Expired";
			}
			
			pr.setTimeLeft(timeLeft);
			return pr;
       }else{
    	   throw new BusinessException(ExceptionCause.NOT_FOUND);
       }		
	}
	
	

	@Override
	@Transactional
	public void projectUpdate(Project pr, int daysToPost, ClientProfile cp) throws BusinessException {
		Date actualDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(actualDate);
		c.add(Calendar.DATE, daysToPost);
		Date expiryDate = c.getTime();
		pr.setPostedDate(actualDate);
		pr.setExpiryDate(expiryDate);
		cp.addProject(pr);
		em.merge(pr);
		em.merge(cp);
	}

	// Task Automatico per aggiornare lo status dei progetti
	@Override
	@Transactional
	public void checkProjectExpired() throws BusinessException {
		
		String querystring = "SELECT p FROM Project p WHERE p.status = :status AND p.expiryTime >= CURRENT_TIMESTAMP";
	
		//logger.info("=========== SCHEDULED TASK =====================");
		TypedQuery<Project> query = em.createQuery(querystring, Project.class);
		query.setParameter("status", ProjectStatus.HIRING_OPEN);
		//logger.info(query);
		List<Project> result = query.getResultList();
		if(!result.isEmpty()){
		for (Project project : result) {
			project.setStatus(ProjectStatus.EXPIRED);
			em.merge(project);
/*			logger.info("////////////////////Sono all'interno del ciclo///////////////////");
			logger.info(project);*/
		  }
		}
		//logger.info("=========== FINE SCHEDULED TASK =====================");
	}

	@Override
	@Transactional
	public void createProposal(Proposal proposal, int projectID, int freelanceUserID) throws BusinessException {
		
        FreelanceProfile aspirantFreelance = em.find(User.class, freelanceUserID).getFreelanceProfile();
        Project project = em.find(Project.class, projectID);
        project.setTotalProposal(project.getTotalProposal()+1);
        profileService.removeProjectToWatchList(aspirantFreelance, project);
        proposal.setAspirantFreelance(aspirantFreelance);
        proposal.setRefProject(project);
        proposal.setProposalDate(new Date());
        proposal.setStatus(ProposalStatus.NOT_HIRED);
		em.persist(proposal);
		inboxService.sendSystemMessage(SystemMessageSubject.NEW_JOB_PROPOSAL, proposal);
		//em.merge(project); ---> non dovrebbe servirci 

	}

	@Override
	public List<Proposal> FindAllProposalByProjectID(int projectID)
			throws BusinessException {
		
		String queryString = "SELECT p FROM Proposal p WHERE p.refProject.projectID = '" + projectID + "'";
		TypedQuery<Proposal> query = em.createQuery(queryString, Proposal.class);
		List<Proposal> result = query.getResultList();
		return result;
	}

	@Override
	public FilterDataResponse<Proposal> findAllProposalsFiltered(FilterDataRequest filterDataRequest)
			throws BusinessException {
		
		int firstResult = filterDataRequest.getItemsForPage()*filterDataRequest.getPage()-filterDataRequest.getItemsForPage();
        int maxResult = filterDataRequest.getItemsForPage();
		String queryString = "SELECT p FROM Proposal p WHERE p.refProject.projectID = '" + filterDataRequest.getProjectID() + "'" + " ORDER BY p.proposalDate " + filterDataRequest.getDirSort();
		TypedQuery<Proposal> typedQuery = em.createQuery(queryString, Proposal.class);
		TypedQuery<Proposal> typedQueryPaginated = em.createQuery(queryString, Proposal.class).setFirstResult(firstResult).setMaxResults(maxResult);
		List<Proposal> proposals = typedQueryPaginated.getResultList();
		int totalItems = typedQuery.getResultList().size();
		FilterDataResponse<Proposal> response = new FilterDataResponse<Proposal>();
		response.setTotalItems(totalItems);
		response.setItems(proposals);
		return response;
	}

	@Override
	public boolean checkAlreadyPostProposal(User user, Project project) throws BusinessException {
		if(user.getFreelanceProfile() != null){
	    String search = "SELECT p FROM Proposal p WHERE p.refProject.projectID = '" + project.getProjectID() + "' AND p.aspirantFreelance.freelanceID = '" + user.getFreelanceProfile().getFreelanceID() + "'";
	    boolean exist = !em.createQuery(search).getResultList().isEmpty();
	    return exist;	
		}else {
			return false;
		}
	}

//	@Override
//	@Transactional
//	public int createProposalQuestionMessage(Message message, int proposalID) throws BusinessException{
//		Proposal proposal = em.find(Proposal.class, proposalID);
//		em.refresh(proposal);
//		inboxService.incrementNewMessageCounter(proposal, proposal.getRefProject().getClientOwner().getUser().getUserID());
//		message.setMessageProposal(proposal);
//	    message.setReceiver(proposal.getAspirantFreelance().getUser());
//		message.setSender(proposal.getRefProject().getClientOwner().getUser());
//		message.setType(MessageType.CLIENT);
//		message.setMessageDate(new Date());
//		message.setNewMessage(true);
//		em.persist(message);
//		em.merge(proposal);    
//		return proposal.getRefProject().getProjectID();
//	}

	@Override
	public Proposal findProposalByID(int proposalID, User userLogged) throws BusinessException {
		Proposal proposal = em.find(Proposal.class, proposalID);
	  if(proposal!=null){	
		if(proposal.getAspirantFreelance().getUser().getUserID() == userLogged.getUserID() | proposal.getRefProject().getClientOwner().getUser().getUserID() == userLogged.getUserID()){
		   return proposal;
		}else{
			throw new BusinessException(ExceptionCause.NOT_AUTHORIZED);
		}
	  }else{
		  throw new BusinessException(ExceptionCause.NOT_FOUND);
	  }
	}

	@Override
	public FilterDataResponse<Project> findAllProjectsFiltered(
			FilterDataRequest filterDataRequest) throws BusinessException {
		
		int firstResult = filterDataRequest.getItemsForPage()*filterDataRequest.getPage()-filterDataRequest.getItemsForPage();
		int maxResult = filterDataRequest.getItemsForPage();
		String baseQuery = "SELECT p FROM Project p WHERE p.status LIKE '"+filterDataRequest.getStatus()+"' ";
		
		String queryUser="";
		if(filterDataRequest.getUserID()!=0){
			queryUser="AND p.clientOwner.user.userID <> "+filterDataRequest.getUserID()+" ";
		}
		
		String querySearch = "";
		if(!"".equals(filterDataRequest.getSearch())){
			querySearch = "AND p.title LIKE '%"+filterDataRequest.getSearch()+"%' ";
		}
		
		String  queryCategory = "";
		if(!"".equals(filterDataRequest.getCategory())){
			queryCategory = "AND p.projectSubCategory.parentCategory.catID = '"+filterDataRequest.getCategory()+"' ";
		}
		
		String querySkill= "";
		if(!"".equals(filterDataRequest.getSkill().getName())){
			String query="SELECT s FROM Skill s WHERE s.name LIKE '"+filterDataRequest.getSkill().getName()+"'";
			TypedQuery<Skill> typedQuery = em.createQuery(query, Skill.class);
			filterDataRequest.setSkill(typedQuery.getSingleResult());
			querySearch = "AND :skill MEMBER OF p.skills ";
		}
		
		String order="";
		if(!"".equals(filterDataRequest.getItemSort())){
			String itemSort="";
			if("name".equals(filterDataRequest.getItemSort())){
				itemSort="p.title";
			}
			if("date".equals(filterDataRequest.getItemSort())){
				itemSort="p.expiryDate";
			}
			if("budget".equals(filterDataRequest.getItemSort())){
				itemSort="p.budgetMin";
			}
			order = "ORDER BY "+itemSort+" "+filterDataRequest.getDirSort();
		}
		String query = baseQuery+queryUser+querySearch+queryCategory+querySkill+order;
		System.out.println(query);
		TypedQuery<Project> typedQuery = em.createQuery(query, Project.class);
		TypedQuery<Project> typedQueryPaginated = em.createQuery(query, Project.class).setFirstResult(firstResult).setMaxResults(maxResult);
		if(!"".equals(filterDataRequest.getSkill().getName())){
		typedQuery.setParameter("skill", filterDataRequest.getSkill());
		typedQueryPaginated.setParameter("skill", filterDataRequest.getSkill());
		}
		int totalItems=typedQuery.getResultList().size();
		List<Project> projects = typedQueryPaginated.getResultList();
		return new FilterDataResponse<Project>(totalItems, projects);	
	}

	@Override
	public FilterDataResponse<Project> findAllClientProjectsFiltered(
			FilterDataRequest filterDataRequest) throws BusinessException {

		int firstResult = filterDataRequest.getItemsForPage()*filterDataRequest.getPage()-filterDataRequest.getItemsForPage();
		int maxResult = filterDataRequest.getItemsForPage();
		String baseQuery="";
		if(filterDataRequest.getStatus().equals("COMPLETED")){
			baseQuery +="SELECT p FROM Proposal prop JOIN prop.refProject p WHERE prop.status LIKE '"+filterDataRequest.getStatus()+"' ";
		}
		else{
			if(filterDataRequest.getStatus().equals("HIRING_CLOSED")){
				baseQuery +="SELECT p FROM Proposal prop JOIN prop.refProject p " +
						"WHERE prop.status LIKE 'WORKING' ";
			}
			else{
				baseQuery += "SELECT p FROM Project p WHERE p.status LIKE '"+filterDataRequest.getStatus()+"' ";
			}
		}
		String queryUser="";
		if(filterDataRequest.getUserID()!=0){
			queryUser="AND p.clientOwner.user.userID = "+filterDataRequest.getUserID()+" ";
		}
		
		String querySearch = "";
		if(!"".equals(filterDataRequest.getSearch())){
			querySearch = "AND p.title LIKE '%"+filterDataRequest.getSearch()+"%' ";
		}
			
		String order="";
		if(!"".equals(filterDataRequest.getItemSort())){
			String itemSort="";
			if("name".equals(filterDataRequest.getItemSort())){
				itemSort="p.title";
			}
			order = "ORDER BY "+itemSort+" "+filterDataRequest.getDirSort();
		}
		String query = baseQuery+queryUser+querySearch+order;
		System.out.println(query);
		TypedQuery<Project> typedQuery = em.createQuery(query, Project.class);
		TypedQuery<Project> typedQueryPaginated = em.createQuery(query, Project.class).setFirstResult(firstResult).setMaxResults(maxResult);
		int totalItems=typedQuery.getResultList().size();
		List<Project> projects = typedQueryPaginated.getResultList();
		return new FilterDataResponse<Project>(totalItems, projects);	
	}
	@Override
	public FilterDataResponse<Project> findAllFreelanceProjectsFiltered(
			FilterDataRequest filterDataRequest) throws BusinessException {

		int firstResult = filterDataRequest.getItemsForPage()*filterDataRequest.getPage()-filterDataRequest.getItemsForPage();
		int maxResult = filterDataRequest.getItemsForPage();
		String status = filterDataRequest.getStatus();
		String baseQuery="";
		String queryUser="";
		if (status.equals("watch")){
			baseQuery+="SELECT p FROM FreelanceProfile fp JOIN fp.projectWatchList p WHERE fp.user.userID = "+filterDataRequest.getUserID()+" ";
		}
		else{
			queryUser+="AND prop.aspirantFreelance.user.userID = "+filterDataRequest.getUserID()+" ";
			baseQuery="SELECT p FROM Proposal prop JOIN prop.refProject p WHERE prop.status LIKE '"+filterDataRequest.getStatus()+"' ";
		
		}
		
		String querySearch = "";
		if(!"".equals(filterDataRequest.getSearch())){
			querySearch = "AND p.title LIKE '%"+filterDataRequest.getSearch()+"%' ";
		}
			
		String order="";
		if(!"".equals(filterDataRequest.getItemSort())){
			String itemSort="";
			if("name".equals(filterDataRequest.getItemSort())){
				itemSort="p.title";
			}
			order = "ORDER BY "+itemSort+" "+filterDataRequest.getDirSort();
		}
		String query = baseQuery+queryUser+querySearch+order;
		System.out.println(query);
		TypedQuery<Project> typedQuery = em.createQuery(query, Project.class);
		TypedQuery<Project> typedQueryPaginated = em.createQuery(query, Project.class).setFirstResult(firstResult).setMaxResults(maxResult);
		int totalItems=typedQuery.getResultList().size();
		List<Project> projects = typedQueryPaginated.getResultList();
		System.out.println(totalItems+" "+projects.size());
		return new FilterDataResponse<Project>(totalItems, projects);	
	}

	@Override
	@Transactional
	public void addFeedback(FeedBack feedback, int projectlID)throws BusinessException {
		Proposal p;
		//System.out.println(feedback.getType());
		if(feedback.getType().equals("client")){
			String queryString = "SELECT p FROM Proposal p " +
					"WHERE p.refProject.projectID="+feedback.getJobEvaluated().getRefProject().getProjectID()+
					" AND p.status LIKE 'WORKING'";
			TypedQuery<Proposal> query = em.createQuery(queryString, Proposal.class);
			p = query.getSingleResult();
			feedback.setJobEvaluated(p);
			Float rating_old=p.getAspirantFreelance().getRating();
			int totalProjects_old= p.getAspirantFreelance().getTotalProjects();
			int totalProjects = totalProjects_old + 1;
			System.out.println("Progetti prima: "+totalProjects_old+" Progetti dopo: "+totalProjects);
			System.out.println("Rating prima: "+rating_old);
			float somma_old = rating_old*totalProjects_old;
			System.out.println("Somma prima: "+somma_old);
			float somma=somma_old+feedback.getGrade();
			System.out.println("Somma dopo: "+somma);
			float rating = somma/totalProjects;
			p.getAspirantFreelance().setTotalProjects(totalProjects);
			p.getAspirantFreelance().setRating(rating);
			feedback.setBeneficiary(p.getAspirantFreelance().getUser());
		}
		else{
			String queryString = "SELECT p FROM Proposal p " +
					"WHERE p.refProject.projectID="+feedback.getJobEvaluated().getRefProject().getProjectID()+
					" AND p.status LIKE 'COMPLETED'";
			TypedQuery<Proposal> query = em.createQuery(queryString, Proposal.class);
			p = query.getSingleResult();
			p.getRefProject().setValuated(true);
			feedback.setJobEvaluated(p);
			ClientProfile c = p.getRefProject().getClientOwner(); 
			Float rating_old=c.getRating();
			int totalProjects_old= c.getTotalProjects();
			int totalProjects = c.getTotalProjects();
			System.out.println("Progetti prima: "+totalProjects_old+" Progetti dopo: "+totalProjects);
			System.out.println("Rating prima: "+rating_old);
			float somma_old = rating_old*totalProjects_old;
			System.out.println("Somma prima: "+somma_old);
			float somma=somma_old+feedback.getGrade();
			System.out.println("Somma dopo: "+somma);
			float rating = somma/totalProjects;
			c.setTotalProjects(totalProjects);
			c.setRating(rating);
			feedback.setBeneficiary(c.getUser());
			
		}
		//em.persist(feedback);
		p.getEvaluations().add(feedback);
		p.setStatus(ProposalStatus.COMPLETED);
		em.merge(p);
	}

	@Override
	public ResponseGrid<Project> findAllProjectPaginated(RequestGrid requestGrid)
			throws BusinessException {
		//creo la query di selezione e ricerca
				String baseSearch = "SELECT p FROM Project p";
				if((!"".equals(requestGrid.getsSearch()))){
					baseSearch += " WHERE p.title LIKE '" + requestGrid.getsSearch() + "%' "; 
				}
				
				//creo la query completa
				String search = baseSearch + " ORDER BY p." + requestGrid.getSortCol() + " " + requestGrid.getSortDir();
				
				//trovo il numero di entità (record) restituiti
				long iTotalRecords = em.createQuery(search).getResultList().size();
				  
				TypedQuery<Project> query = em.createQuery(search, Project.class).setFirstResult(requestGrid.getiDisplayStart()).setMaxResults(requestGrid.getiDisplayLength());
		        List<Project> projects = query.getResultList();
		        
				return new ResponseGrid<Project>(requestGrid.getsEcho(), iTotalRecords, iTotalRecords, projects);
			
	}

/*	@Override
	public Proposal findProposalByProjectAndFreelance(Project project,
			FreelanceProfile freelance) throws BusinessException {
				
		String queryString = "SELECT p FROM Proposal p WHERE p.refProject = " + project + " AND p.aspirantFreelance= " + freelance;
		
        TypedQuery<Proposal> = 
		
		return null;
	}*/

	
}
