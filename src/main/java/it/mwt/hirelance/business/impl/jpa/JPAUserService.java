package it.mwt.hirelance.business.impl.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.BusinessException.ExceptionCause;
import it.mwt.hirelance.business.FilterDataRequest;
import it.mwt.hirelance.business.FilterDataResponse;
import it.mwt.hirelance.business.ImageService;
import it.mwt.hirelance.business.RequestGrid;
import it.mwt.hirelance.business.ResponseGrid;
import it.mwt.hirelance.business.UserService;
import it.mwt.hirelance.business.model.ClientProfile;
import it.mwt.hirelance.business.model.FeedBack;
import it.mwt.hirelance.business.model.FreelanceProfile;
import it.mwt.hirelance.business.model.Role;
import it.mwt.hirelance.business.model.Skill;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.common.util.MD5Hash;

@Service
public class JPAUserService implements UserService {

	
	@Value("#{uploadPaths.clientFolder}")
	private String clientFolder;
	
	@Value("#{uploadPaths.freelancerFolder}")
	private String freelancerFolder;
	
	@Value("#{uploadPaths.basePath}")
	private String basePath;
	
	@Autowired
	private ImageService imageService;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void create(User user) throws BusinessException {
		Role role = em.find(Role.class, 2);
		user.setRole(role);
		Date d = new Date();
		user.setCreationDate(d);
		user.setPassword(MD5Hash.md5(user.getPassword()));
		em.persist(user);		
	}

	@Override
	public ResponseGrid<User> findAllUserPaginated(RequestGrid requestGrid) throws BusinessException {
		
		//creo la query di selezione e ricerca
		String baseSearch = "SELECT u FROM User u";
		if((!"".equals(requestGrid.getsSearch()))){
			baseSearch += " WHERE u.name LIKE '" + requestGrid.getsSearch() + "%' " 
					        + "OR u.username LIKE '" + requestGrid.getsSearch() + "%' "
					        + "OR u.surname LIKE '" + requestGrid.getsSearch() + "%' ";	
		}
		
		//creo la query completa
		String search = baseSearch + " ORDER BY u." + requestGrid.getSortCol() + " " + requestGrid.getSortDir();
		
		//trovo il numero di entità (record) restituiti
		long iTotalRecords = em.createQuery(search).getResultList().size();
		  
		TypedQuery<User> query = em.createQuery(search, User.class).setFirstResult(requestGrid.getiDisplayStart()).setMaxResults(requestGrid.getiDisplayLength());
        List<User> users = query.getResultList();
		return new ResponseGrid<User>(requestGrid.getsEcho(), iTotalRecords, iTotalRecords, users);
	}

	@Override
	public User findClientUser(int userID) throws BusinessException {
		User user = em.find(User.class, userID);  
	    if(user!=null && user.getClientProfile()!=null){
			return user;
		}else{		   
			throw new BusinessException(ExceptionCause.NOT_FOUND);
		}
	}
	
	@Override
	public User findFreelancerUser(int userID) throws BusinessException {
		User user = em.find(User.class, userID);  
		if(user!=null && user.getFreelanceProfile()!=null){
			return user;
		}else{
		   throw new BusinessException(ExceptionCause.NOT_FOUND);
		}
	}

	@Override
	public User findUserById(int userID){
	       User user = em.find(User.class, userID);
	       return user;
	}
	
	@Override
	public User findUserByUsername(String username) throws BusinessException {
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.username LIKE '"+username+"'", User.class);
       if(!username.equals("anonymousUser")){
		User user = q.getResultList().get(0);
             return user;
          }else {
		     return null;
	    }

	}
	
	@Override
	@Transactional
	public void delete(User user) throws BusinessException {
		//System.out.println("Cancello i file dell'utente");
		imageService.deleteImages(user.getUserID()+"/");
		em.remove(em.merge(user));
	}

	@Override
	@Transactional
	public void update(User user) throws BusinessException {
		user.setPassword(MD5Hash.md5(user.getPassword()));
		em.merge(user);
	}

	@Override
	public boolean existAlreadyUser(String username) throws BusinessException {
	    String search = "SELECT u.userID FROM User u WHERE u.username = '" + username + "'";
	    boolean exist = !em.createQuery(search).getResultList().isEmpty();
	    return exist;
	}

	@Override
	public boolean existAlreadyEmail(String email) throws BusinessException {
	    String search = "SELECT u.userID FROM User u WHERE u.email = '" + email + "'";
	    boolean exist = !em.createQuery(search).getResultList().isEmpty();
	    return exist;
	}

	@Override
	@Transactional
	public void addFreelancerProfile(User u,FreelanceProfile fp) throws BusinessException {
		u.setFreelanceProfile(fp);
		em.merge(u);
	}
	
	@Override
	@Transactional
	public void removeFreelancerProfile(User u) throws BusinessException {
		
		//System.out.println("Sto cancellando le immagini in "+freelancerFolder);
		imageService.deleteImages(u.getUserID()+freelancerFolder);
		//System.out.println("Ora setto null il freelance profile");
		u.setFreelanceProfile(null);
		em.merge(u);
	}
	
	@Override
	@Transactional
	public void addClientProfile(User u,ClientProfile cp) throws BusinessException {
		u.setClientProfile(cp);
		em.merge(u);
	}

	@Override
	public List<User> findAllFreelancers() throws BusinessException {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.freelanceProfile IS NOT NULL", User.class);
		return query.getResultList();
	}

	@Override
	public FilterDataResponse<User> findAllFreelancersFiltered(
			FilterDataRequest filterDataRequest) throws BusinessException {
		
		int firstResult = filterDataRequest.getItemsForPage()*filterDataRequest.getPage()-filterDataRequest.getItemsForPage();
		int maxResult = filterDataRequest.getItemsForPage();
		String baseQuery = "SELECT u FROM User u WHERE u.freelanceProfile IS NOT NULL ";
		
		String queryUser="";
		if(filterDataRequest.getUserID()!=0){
			queryUser="AND u.userID <> "+filterDataRequest.getUserID()+" ";
		}
		
		String querySearch = "";
		if(!"".equals(filterDataRequest.getSearch())){
			querySearch = "AND u.freelanceProfile.freelanceName LIKE '%"+filterDataRequest.getSearch()+"%' ";
		}
		
		String  queryCategory = "";
		if(!"".equals(filterDataRequest.getCategory())){
			queryCategory = "AND u.freelanceProfile.category.catID = '"+filterDataRequest.getCategory()+"' ";
		}
		
		String querySkill= "";
		if(!"".equals(filterDataRequest.getSkill().getName())){
			String query="SELECT s FROM Skill s WHERE s.name LIKE '"+filterDataRequest.getSkill().getName()+"'";
			TypedQuery<Skill> typedQuery = em.createQuery(query, Skill.class);
			filterDataRequest.setSkill(typedQuery.getSingleResult());
			querySearch = "AND :skill MEMBER OF u.freelanceProfile.curriculum.cvSkills ";
		}
		
		String order="";
		if(!"".equals(filterDataRequest.getItemSort())){
			String itemSort="";
			if("name".equals(filterDataRequest.getItemSort())){
				itemSort="u.freelanceProfile.freelanceName";
			}
			if("jobs".equals(filterDataRequest.getItemSort())){
				itemSort="u.freelanceProfile.totalProjects";
			}
			if("rating".equals(filterDataRequest.getItemSort())){
				itemSort="u.freelanceProfile.rating";
			}
			order = "ORDER BY "+itemSort+" "+filterDataRequest.getDirSort();
		}
		String query = baseQuery+queryUser+querySearch+queryCategory+querySkill+order;
		System.out.println(query);
		TypedQuery<User> typedQuery = em.createQuery(query, User.class);
		TypedQuery<User> typedQueryPaginated = em.createQuery(query, User.class).setFirstResult(firstResult).setMaxResults(maxResult);
		if(!"".equals(filterDataRequest.getSkill().getName())){
		typedQuery.setParameter("skill", filterDataRequest.getSkill());
		typedQueryPaginated.setParameter("skill", filterDataRequest.getSkill());
		}
		int totalItems=typedQuery.getResultList().size();
		List<User> freelancers = typedQueryPaginated.getResultList();
		return new FilterDataResponse<User>(totalItems, freelancers);
	}

	@Override
	@Transactional
	public void removeClientProfile(User u) {
		u.setClientProfile(null);
		em.merge(u);
		
	}

	@Override
	public FilterDataResponse<User> findAllClientsFiltered(
			FilterDataRequest filterDataRequest) throws BusinessException {
		int firstResult = filterDataRequest.getItemsForPage()*filterDataRequest.getPage()-filterDataRequest.getItemsForPage();
		int maxResult = filterDataRequest.getItemsForPage();
		String baseQuery = "SELECT u FROM User u WHERE u.clientProfile IS NOT NULL ";
		
		String queryUser="";
		if(filterDataRequest.getUserID()!=0){
			queryUser="AND u.userID <> "+filterDataRequest.getUserID()+" ";
		}
		
		String querySearch = "";
		if(!"".equals(filterDataRequest.getSearch())){
			querySearch = "AND u.clientProfile.clientName LIKE '%"+filterDataRequest.getSearch()+"%' " +
					"OR u.clientProfile.companyName LIKE '%"+filterDataRequest.getSearch()+"%' ";
		}
		
		String order="";
		if(!"".equals(filterDataRequest.getItemSort())){
			String itemSort="";
			if("name".equals(filterDataRequest.getItemSort())){
				itemSort="u.clientProfile.clientName";
			}
			if("jobs".equals(filterDataRequest.getItemSort())){
				itemSort="u.clientProfile.totalProjects";
			}
			if("rating".equals(filterDataRequest.getItemSort())){
				itemSort="u.clientProfile.rating";
			}
			order = "ORDER BY "+itemSort+" "+filterDataRequest.getDirSort();
		}
		String query = baseQuery+queryUser+querySearch+order;
		System.out.println(query);
	
		TypedQuery<User> typedQuery = em.createQuery(query, User.class);
		int totalItems=typedQuery.getResultList().size();
		
		TypedQuery<User> typedQueryPaginated = em.createQuery(query, User.class).setFirstResult(firstResult).setMaxResults(maxResult);
		List<User> clients = typedQueryPaginated.getResultList();
		
		/*for(int i =0;i<clients.size();i++){
			clients.get(i).getClientProfile().setTotalProjects(clients.get(i).getClientProfile().getProjects().size());
		}*/
		
		return new FilterDataResponse<User>(totalItems, clients);
	}

	@Override
	public List<User> findUserByEmail(String userEmail) throws BusinessException {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email LIKE '"+userEmail+"'", User.class);
		List<User> users = query.getResultList();
		return users;
		
	}
	@Override
	public List<FeedBack> findAllFeedback(int userID) throws BusinessException{
		TypedQuery<FeedBack> query = em.createQuery("SELECT f FROM FeedBack f WHERE f.beneficiary.userID = "+userID
				+" AND f.type LIKE 'client'",FeedBack.class);
		return query.getResultList();
	}
}
