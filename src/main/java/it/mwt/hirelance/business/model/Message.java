package it.mwt.hirelance.business.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity

@Table(name = "MESSAGES")
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3488590816583709441L;
	private int messageID;
	private String text;
	private User sender;
	private User receiver;
	private MessageSenderType senderType;
	private SystemMessageSubject systemMessageSubject;
	//private User recipier;
	private MessageType messageType;
	private boolean newMessage;
	private Date messageDate;
	private Proposal messageProposal;

	public Message() {
		super();
	}

	@Id
	@Column(name = "MESSAGE_ID")
	@GeneratedValue(generator = "Message_Gen")
	@SequenceGenerator(name = "Message_Gen", sequenceName = "Message_Seq", initialValue=1, allocationSize=1)
	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@ManyToOne
	@JoinColumn(name = "SENDER_FK", referencedColumnName = "USER_ID")
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@ManyToOne
	@JoinColumn(name = "RECEIVER_FK", referencedColumnName = "USER_ID")
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public boolean isNewMessage() {
		return newMessage;
	}

	public void setNewMessage(boolean newMessage) {
		this.newMessage = newMessage;
	}

	@Temporal(TIMESTAMP)
	public Date getMessageDate() {
		return messageDate;
	}


	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	@ManyToOne
	@JoinColumn(name = "PROPOSAL_FK", referencedColumnName = "PROPOSAL_ID")
	public Proposal getMessageProposal() {
		return messageProposal;
	}


	public void setMessageProposal(Proposal messageProposal) {
		this.messageProposal = messageProposal;
	}
	
	@Enumerated(EnumType.STRING)
	public MessageSenderType getSenderType() {
		return senderType;
	}

	public void setSenderType(MessageSenderType senderType) {
		this.senderType = senderType;
	}

	
	@Enumerated(EnumType.STRING)
	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	@Enumerated(EnumType.STRING)
	public SystemMessageSubject getSystemMessageSubject() {
		return systemMessageSubject;
	}

	public void setSystemMessageSubject(SystemMessageSubject systemMessageSubject) {
		this.systemMessageSubject = systemMessageSubject;
	}


	public enum MessageType{
		PROPOSAL("Message relative to a Proposa"),
		WORKROOM("Message Relative to a Workroom"),
		SYSTEM_MESSAGE("Sytem Message");
		
		private String type;

		private MessageType(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return type;
		}	
	}

	public enum MessageSenderType{
		FREELANCE("The sender of message is the Freelance"),
		CLIENT("The sender of message is the Client"),
		SYSTEM("New message from Hirelance System");
		
		private String type;

		private MessageSenderType(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return type;
		}	
	}
	
	public enum SystemMessageSubject{
		NEW_JOB_PROPOSAL("A Freelance has just submit a new proposal for your job"),
		HIRED_FOR_JOB("The Client has selected your proposal and just hire you for his job");
		
		private String subject;

		private SystemMessageSubject(String subject) {
			this.subject = subject;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return subject;
		}	
	}
}
