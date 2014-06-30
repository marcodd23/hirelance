package it.mwt.hirelance.business;

import it.mwt.hirelance.business.model.Proposal;
import it.mwt.hirelance.business.model.User;

public interface WorkRoomService {

	int createWorkRoom(int proposalID) throws BusinessException;
	FilterDataResponse<Proposal> findActiveWorkRoom(int projectID, User userLogged)throws BusinessException;
	//void sendWorkRoomMessage(String messageText, int senderID, int projectID) throws BusinessException;
	//FilterDataResponse<Message> findAllWorkRoomMessages(FilterDataRequest filterDataRequest)throws BusinessException;
	//int findAllNewMessages(int userID) throws BusinessException; 
	
}
