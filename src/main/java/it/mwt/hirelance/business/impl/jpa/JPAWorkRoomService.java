package it.mwt.hirelance.business.impl.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.BusinessException.ExceptionCause;
import it.mwt.hirelance.business.FilterDataResponse;
import it.mwt.hirelance.business.InboxService;
import it.mwt.hirelance.business.UserService;
import it.mwt.hirelance.business.WorkRoomService;
import it.mwt.hirelance.business.model.Message.SystemMessageSubject;
import it.mwt.hirelance.business.model.Project;
import it.mwt.hirelance.business.model.Project.ProjectStatus;
import it.mwt.hirelance.business.model.Proposal;
import it.mwt.hirelance.business.model.Proposal.ProposalStatus;
import it.mwt.hirelance.business.model.User;

@Service
public class JPAWorkRoomService implements WorkRoomService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserService userService;
	
	@Autowired
	private InboxService inboxService;

	@Override
	@Transactional
	public int createWorkRoom(int proposalID) throws BusinessException {

		Proposal proposal = em.find(Proposal.class, proposalID);
		// WorkRoom workroom = new WorkRoom();
		// workroom.setProposal(proposal);
		proposal.setStatus(ProposalStatus.WORKING);
		proposal.setJobStartDate(new Date());
		Project p = proposal.getRefProject();
		p.setStatus(ProjectStatus.HIRING_CLOSED);

		em.merge(p);
		em.merge(proposal);
		
		inboxService.sendSystemMessage(SystemMessageSubject.HIRED_FOR_JOB, proposal);

		return proposal.getRefProject().getProjectID();
	}

	@Override
	public FilterDataResponse<Proposal> findActiveWorkRoom(int projectID,
			User userLogged) throws BusinessException {
		// return em.find(WorkRoom.class, projectID);
		String queryString = "SELECT p FROM Proposal p WHERE p.refProject.projectID = '"
				+ projectID + "' AND p.status = :status";
		// Query query = em.createQuery(queryString);
		// query.setParameter("status", ProposalStatus.WORKING);
		TypedQuery<Proposal> query = em
				.createQuery(queryString, Proposal.class);
		query.setParameter("status", ProposalStatus.WORKING);
		List<Proposal> proposal = query.getResultList();
		if (!proposal.isEmpty()) {
			if (proposal.get(0).getAspirantFreelance().getUser().getUserID() == userLogged.getUserID()
					| proposal.get(0).getRefProject().getClientOwner().getUser().getUserID() == userLogged.getUserID()) {
                  
				FilterDataResponse<Proposal> result = new FilterDataResponse<Proposal>();
				result.setItems(proposal);
				return result;
			}else{
				throw new BusinessException(ExceptionCause.NOT_AUTHORIZED);
			}

		} else {
			throw new BusinessException(ExceptionCause.NOT_FOUND);
			// throw new
			// BusinessException("There are not active WorkRoom for this project");
		}
	}

	/*
	 * @Override
	 * 
	 * @Transactional public void sendWorkRoomMessage(String messageText, int
	 * senderID, int projectID) throws BusinessException{
	 * 
	 * Message message = new Message(); final Proposal proposal =
	 * findActiveWorkRoom(projectID).getItems().get(0); final User sender =
	 * userService.findUserById(senderID);
	 * 
	 * if(proposal.getAspirantFreelance().equals(sender.getFreelanceProfile())){
	 * message.setSender(sender);
	 * message.setReceiver(proposal.getRefProject().getClientOwner().getUser());
	 * message.setType(MessageType.FREELANCE); }else{ message.setSender(sender);
	 * message.setReceiver(proposal.getAspirantFreelance().getUser());
	 * message.setType(MessageType.CLIENT); }
	 * 
	 * message.setText(messageText); message.setMessageProposal(proposal);
	 * message.setNewMessage(true); message.setMessageDate(new Date()); try {
	 * em.persist(message);
	 * 
	 * } catch (Exception e) { throw new BusinessException(e); }
	 * 
	 * }
	 */

	/*
	 * @Override public FilterDataResponse<Message>
	 * findAllWorkRoomMessages(FilterDataRequest filterDataRequest) throws
	 * BusinessException {
	 * 
	 * int firstResult =
	 * filterDataRequest.getItemsForPage()*filterDataRequest.getPage
	 * ()-filterDataRequest.getItemsForPage(); int maxResult =
	 * filterDataRequest.getItemsForPage(); String queryString =
	 * "SELECT m FROM Message m WHERE m.messageProposal.proposalID = '" +
	 * filterDataRequest.getProposalID() + "' ORDER BY m.messageDate DESC";
	 * TypedQuery<Message> typedQuery = em.createQuery(queryString,
	 * Message.class); TypedQuery<Message> typedQueryPaginated =
	 * em.createQuery(queryString,
	 * Message.class).setFirstResult(firstResult).setMaxResults(maxResult); int
	 * totalItems = typedQuery.getResultList().size(); List<Message> messages =
	 * typedQueryPaginated.getResultList(); FilterDataResponse<Message> response
	 * = new FilterDataResponse<Message>(); response.setTotalItems(totalItems);
	 * response.setItems(messages); return response; }
	 */

	/*
	 * @Override public int findAllNewMessages(int userID) throws
	 * BusinessException {
	 * 
	 * String queryString =
	 * "SELECT m FROM Message m WHERE m.receiver.userID = '" + userID +
	 * "' AND m.newMessage = TRUE"; return 0; }
	 */

}
