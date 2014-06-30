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
import it.mwt.hirelance.business.FilterDataRequest;
import it.mwt.hirelance.business.FilterDataResponse;
import it.mwt.hirelance.business.InboxService;
import it.mwt.hirelance.business.ProjectService;
import it.mwt.hirelance.business.UserService;
import it.mwt.hirelance.business.model.Message;
import it.mwt.hirelance.business.model.Message.MessageSenderType;
import it.mwt.hirelance.business.model.Message.SystemMessageSubject;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.business.model.Message.MessageType;
import it.mwt.hirelance.business.model.Proposal;
import it.mwt.hirelance.business.model.Proposal.ProposalStatus;

@Service
public class JPAInboxService implements InboxService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService pService;

	@Override
	public FilterDataResponse<Proposal> findAllConversations(
			FilterDataRequest filterDataRequest) throws BusinessException {
		int userID = filterDataRequest.getUserID();
		int firstResult = filterDataRequest.getItemsForPage()
				* filterDataRequest.getPage()
				- filterDataRequest.getItemsForPage();
		int maxResult = filterDataRequest.getItemsForPage();

		String query = "SELECT DISTINCT m.messageProposal FROM Message m WHERE (m.receiver.userID = '" + userID + "' OR m.sender.userID = '" + userID + "') "
				+ "AND m.messageProposal.status = :proposalStatus AND m.messageType != :messageType ORDER BY m.messageDate DESC";
		TypedQuery<Proposal> typedQuery = em.createQuery(query, Proposal.class);
		TypedQuery<Proposal> typedQueryPaginated = em.createQuery(query, Proposal.class).setFirstResult(firstResult).setMaxResults(maxResult);
		typedQuery.setParameter("messageType", MessageType.SYSTEM_MESSAGE);
		typedQueryPaginated.setParameter("messageType", MessageType.SYSTEM_MESSAGE);
		if (filterDataRequest.getConversationType().equals("inbox")) {
			typedQuery.setParameter("proposalStatus", ProposalStatus.NOT_HIRED);
			typedQueryPaginated.setParameter("proposalStatus",
					ProposalStatus.NOT_HIRED);
		} else {
			typedQuery.setParameter("proposalStatus", ProposalStatus.WORKING);
			typedQueryPaginated.setParameter("proposalStatus",
					ProposalStatus.WORKING);
		}
		int totalItems = typedQuery.getResultList().size();
		List<Proposal> proposals = typedQueryPaginated.getResultList();
		FilterDataResponse<Proposal> response = new FilterDataResponse<Proposal>();
		response.setTotalItems(totalItems);
		response.setItems(proposals);
		return response;
	}

	@Override
	@Transactional
	public FilterDataResponse<Message> findAllInboxMessages(FilterDataRequest filterDataRequest) throws BusinessException {
		int proposalID = filterDataRequest.getProposalID();
		Proposal proposal = null;
		int firstResult = filterDataRequest.getItemsForPage() * filterDataRequest.getPage() - filterDataRequest.getItemsForPage();
		int maxResult = filterDataRequest.getItemsForPage();
		String query;
		TypedQuery<Message> typedQuery;
		TypedQuery<Message> typedQueryPaginated;
		if (filterDataRequest.getConversationType() != null && filterDataRequest.getConversationType().equals("system")) {
			//Se cerchiamo messaggi di Sistema
			int userID = filterDataRequest.getUserID();
			query = "SELECT m FROM Message m WHERE m.receiver.userID = '"
					+ userID + "' AND m.senderType = :senderType "
					+ "AND m.newMessage = TRUE"
					+ " ORDER BY m.messageDate DESC";
			typedQuery = em.createQuery(query, Message.class);
			typedQueryPaginated = em.createQuery(query, Message.class).setFirstResult(firstResult).setMaxResults(maxResult);
			typedQuery.setParameter("senderType", MessageSenderType.SYSTEM);
			typedQueryPaginated.setParameter("senderType", MessageSenderType.SYSTEM);
		} else {
			//Se cerchiamo messaggi di Tra Frellance e Client
			proposal = em.find(Proposal.class, proposalID);
			query = "SELECT m FROM Message m WHERE m.messageProposal.proposalID = '"
					+ proposalID + "' AND m.senderType != :senderType ORDER BY m.messageDate DESC";

			typedQuery = em.createQuery(query, Message.class);
			typedQueryPaginated = em.createQuery(query, Message.class).setFirstResult(firstResult).setMaxResults(maxResult);
			typedQuery.setParameter("senderType", MessageSenderType.SYSTEM);
			typedQueryPaginated.setParameter("senderType", MessageSenderType.SYSTEM);
		}

		int totalItems = typedQuery.getResultList().size();
		List<Message> messages = typedQueryPaginated.getResultList();
		FilterDataResponse<Message> response = new FilterDataResponse<Message>();
		response.setTotalItems(totalItems);
		response.setItems(messages);

		if(proposal!=null){
		// Azzero il contatore nuovi messaggi
		resetNewMessageCounter(proposal, filterDataRequest.getUserID());
		// Metto a false il "NewMessage"
		for (Message message : messages) {
			if (message.isNewMessage() && (message.getReceiver().getUserID() == filterDataRequest.getUserID())) {
				message.setNewMessage(false);
				em.merge(message);
			}
		 }
		}

		return response;
	}

	
	@Override
	@Transactional
	public void sendMessage(String messageText, int senderID, int proposalID)
			throws BusinessException {
		Message message = new Message();
		User sender = userService.findUserById(senderID);
		Proposal proposal = pService.findProposalByID(proposalID, sender);

		if (sender.getFreelanceProfile() != null
				&& (proposal.getAspirantFreelance().getFreelanceID() == sender.getFreelanceProfile().getFreelanceID())) {
			System.out.println("Il mittente del messaggio è il freelance della proposal");
			message.setReceiver(proposal.getRefProject().getClientOwner().getUser());
			message.setSenderType(MessageSenderType.FREELANCE);
		} else {
			System.out.println("Il mittente del messaggio è il client del progetto relativo alla proposal");
			message.setReceiver(proposal.getAspirantFreelance().getUser());
			message.setSenderType(MessageSenderType.CLIENT);
		}

		if (proposal.getStatus().equals(ProposalStatus.NOT_HIRED)) {
			message.setMessageType(MessageType.PROPOSAL);
		} else {
			message.setMessageType(MessageType.WORKROOM);
		}
		message.setSender(sender);
		message.setText(messageText);
		message.setMessageProposal(proposal);
		message.setNewMessage(true);
		message.setMessageDate(new Date());
		// Incremento contatore nuovi messaggi
		incrementNewMessageCounter(proposal, senderID);
		
		try {
			em.persist(message);
			em.merge(proposal);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	@Transactional
	public void sendSystemMessage(SystemMessageSubject systemMessageSubject,
			Proposal proposal) {
		Message message = new Message();
		message.setSenderType(MessageSenderType.SYSTEM);
		message.setMessageType(MessageType.SYSTEM_MESSAGE);
		message.setNewMessage(true);
		message.setMessageDate(new Date());
		message.setMessageProposal(proposal);
		if (systemMessageSubject.equals(SystemMessageSubject.NEW_JOB_PROPOSAL)) {
			message.setReceiver(proposal.getRefProject().getClientOwner().getUser());
			message.setSystemMessageSubject(SystemMessageSubject.NEW_JOB_PROPOSAL);
			message.setText(SystemMessageSubject.NEW_JOB_PROPOSAL.toString());
			proposal.getRefProject().getClientOwner().getUser().incrementNewSystemMessageCounter();
		} else {
			message.setReceiver(proposal.getAspirantFreelance().getUser());
			message.setSystemMessageSubject(SystemMessageSubject.HIRED_FOR_JOB);
			message.setText(SystemMessageSubject.HIRED_FOR_JOB.toString());
			proposal.getAspirantFreelance().getUser().incrementNewSystemMessageCounter();
		}
		em.persist(message);
	}

	@Transactional
	@Override
	public void resetNewMessageCounter(Proposal proposal, int userLoggedID)
			throws BusinessException {

		em.refresh(proposal);
		if (proposal.getAspirantFreelance().getUser().getUserID() == userLoggedID) {
			proposal.resetNewMessageFreelanceCounter();
		} else {
			proposal.resetNewMessageClientCounter();
		}
		em.merge(proposal);
        
	}

	@Transactional
	@Override
	public void incrementNewMessageCounter(Proposal proposal, int userLoggedID)
			throws BusinessException {

		em.refresh(proposal);

		if (proposal.getAspirantFreelance().getUser().getUserID() == userLoggedID) {
			proposal.incrementNewMessageClientCounter();
		} else {
			proposal.incrementNewMessageFreelanceCounter();
		}

		em.merge(proposal);
	}

	@Override
	public int findTotalNewMessages(int userID) throws BusinessException {

		String queryString = "SELECT m FROM Message m WHERE m.receiver.userID = '"
				+ userID + "' AND m.newMessage = TRUE";

		TypedQuery<Message> query = em.createQuery(queryString, Message.class);
		int numberNewMessages = query.getResultList().size();

		return numberNewMessages;
	}

	@Override
	@Transactional
	public boolean decrementSystemNewMessage(int messageID) {
		
		Message message = em.find(Message.class, messageID);
		User receiver = message.getReceiver();
		message.setNewMessage(false);
		receiver.decrementNewSystemMessageCounter();
		em.merge(message);
		em.merge(receiver);
		if(message.getSystemMessageSubject().equals(SystemMessageSubject.NEW_JOB_PROPOSAL)){
		       return true;
        	}else {
				return false;
			}
	}
	
	


}
