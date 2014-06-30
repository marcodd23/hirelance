package it.mwt.hirelance.business;

import it.mwt.hirelance.business.model.ClientProfile;
import it.mwt.hirelance.business.model.FeedBack;
import it.mwt.hirelance.business.model.FreelanceProfile;
import it.mwt.hirelance.business.model.User;
import java.util.List;
public interface UserService {

	void create(User user) throws BusinessException;
    ResponseGrid<User> findAllUserPaginated(RequestGrid requestGrid) throws BusinessException;
    User findFreelancerUser(int userID) throws BusinessException;
    User findClientUser(int userID) throws BusinessException;
	User findUserById(int userID);
	void delete(User u) throws BusinessException;
	void update(User u) throws BusinessException;
	boolean existAlreadyUser(String username) throws BusinessException;
	boolean existAlreadyEmail(String email) throws BusinessException;
	User findUserByUsername(String username) throws BusinessException;
	void addFreelancerProfile(User u, FreelanceProfile fp) throws BusinessException;
	void addClientProfile(User u, ClientProfile cp) throws BusinessException;
	List<User> findAllFreelancers() throws BusinessException;
	FilterDataResponse<User> findAllFreelancersFiltered(FilterDataRequest filterDataRequest) throws BusinessException;
	void removeFreelancerProfile(User u) throws BusinessException;
	void removeClientProfile(User u);
	FilterDataResponse<User> findAllClientsFiltered(FilterDataRequest filterDataRequest) throws BusinessException;
	List<User> findUserByEmail(String userEmail) throws BusinessException;
	List<FeedBack> findAllFeedback(int userID) throws BusinessException;
	
}
