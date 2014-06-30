package it.mwt.hirelance.business;

import it.mwt.hirelance.business.model.ClientProfile;
import it.mwt.hirelance.business.model.FreelanceProfile;
import it.mwt.hirelance.business.model.Proposal;
import it.mwt.hirelance.business.model.UploadedFile;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.business.model.Project;

public interface ProfileService {	
	FreelanceProfile findFreelanceProfileById(int freelanceID) throws BusinessException;
	User createFreelancerProfile(FreelanceProfile fp, User user) throws BusinessException;
	void updateFreelancerProfile(FreelanceProfile fp, User user, UploadedFile image) throws BusinessException;
	User createClientProfile(ClientProfile cp,User user) throws BusinessException;
	void updateClientProfile(ClientProfile cp,User user, UploadedFile image) throws BusinessException;
	void deleteFreelanceProfile(FreelanceProfile fp) throws BusinessException;
	void addProjectToWatchList(FreelanceProfile fp, Project project) throws BusinessException;
	void removeProjectToWatchList(FreelanceProfile fp, Project findProjectByID) throws BusinessException;
	FilterDataResponse<Proposal> findAllProposalFiltered(FilterDataRequest filterDataRequest) throws BusinessException;

}
