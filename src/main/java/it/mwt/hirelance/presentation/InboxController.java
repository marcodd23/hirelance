package it.mwt.hirelance.presentation;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.BusinessException.ExceptionCause;
import it.mwt.hirelance.business.FilterDataRequest;
import it.mwt.hirelance.business.FilterDataResponse;
import it.mwt.hirelance.business.InboxService;
import it.mwt.hirelance.business.ProjectService;
import it.mwt.hirelance.business.model.Message;
import it.mwt.hirelance.business.model.Proposal;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.common.spring.security.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inbox")
public class InboxController {

	@Autowired
	private InboxService inboxService;
	
	@Autowired
	private ProjectService pService;
	
	@RequestMapping("/views")
	public String inboxViews(Model model) throws BusinessException{
		UserDetailsImpl userLogged = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userLogged.getUser();	
		//model.addAttribute("userLogged", user);
		int freelanceProfileID = 0;
		int clientProfileID = 0;
		if(user.getFreelanceProfile()!=null){
			freelanceProfileID = user.getFreelanceProfile().getFreelanceID();
		}
		if(user.getClientProfile() != null){
			clientProfileID = user.getClientProfile().getClientID();
		}
		model.addAttribute("userLogged", user.getUserID());
		model.addAttribute("freelanceProfileID", freelanceProfileID);
		model.addAttribute("clientProfileID", clientProfileID);
		model.addAttribute("filterDataRequest", new FilterDataRequest());	
		return "inbox.views";
	}
	
	@RequestMapping("/conversation")
	public String inboxConversationViews(Model model, @RequestParam("proposalID") int proposalID, final RedirectAttributes redirectAttributes)
	throws BusinessException{
		
		UserDetailsImpl userDetail = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userLogged = userDetail.getUser();	
		Proposal proposal;
	//	try {
			proposal = pService.findProposalByID(proposalID, userLogged);
			int loggedFreelanceProfileID = 0;
			int loggedClientProfileID = 0;
			if(userLogged.getFreelanceProfile()!=null){
				loggedFreelanceProfileID = userLogged.getFreelanceProfile().getFreelanceID();
			}
			if(userLogged.getClientProfile() != null){
				loggedClientProfileID = userLogged.getClientProfile().getClientID();
			}
			
			model.addAttribute("userLogged", userLogged.getUserID());
			model.addAttribute("loggedFreelanceProfileID", loggedFreelanceProfileID);
			model.addAttribute("loggedClientProfileID", loggedClientProfileID);
			model.addAttribute("filterDataRequest", new FilterDataRequest());
            model.addAttribute("proposal", proposal);
            //model.addAttribute("messageActionUri", "/inbox/message/send");
			return "inbox.conversation";
/*		} catch (BusinessException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getExceptionCause().toString());
			if(e.getExceptionCause().equals(ExceptionCause.NOT_AUTHORIZED)){
				redirectAttributes.addFlashAttribute("errorNumber", "403");
			}else{
				redirectAttributes.addFlashAttribute("errorNumber", "404");
			}
			return "redirect:/error";
		}*/

	}
		
	
	@RequestMapping("/allConversationsInbox")
	public @ResponseBody FilterDataResponse<Proposal> findAllConversationInbox(@ModelAttribute FilterDataRequest filterDataRequest) throws BusinessException{
		
		return inboxService.findAllConversations(filterDataRequest);
	}
	
	
	@RequestMapping("/message/allInboxMessages")
	public @ResponseBody FilterDataResponse<Message> findAllInboxMessages(@ModelAttribute FilterDataRequest filterDataRequest) throws BusinessException{ 
		
		return inboxService.findAllInboxMessages(filterDataRequest);
	}
	
	
	@RequestMapping("/message/send")
	public @ResponseBody boolean sendMessage(@RequestParam("messageText") String messageText,
			@RequestParam("userID") int senderID,
			@RequestParam("proposalID") int proposalID) throws BusinessException{
		
		try {
			inboxService.sendMessage(messageText, senderID, proposalID);
			return true;
		} catch (BusinessException e) {
			return false;
		}
	}
	
	
	@RequestMapping("/findTotalNewMessages")
	public @ResponseBody int inboxTotalNewMessages(@RequestParam(value="userID", required=true) int userID) throws BusinessException{		
		return inboxService.findTotalNewMessages(userID);
	}
	
	@RequestMapping("/decrementSystemNewMessage")
	public @ResponseBody boolean decrementSystemNewMessage(@RequestParam(value="messageID") int messageID){
		return inboxService.decrementSystemNewMessage(messageID);
	}
	
}
