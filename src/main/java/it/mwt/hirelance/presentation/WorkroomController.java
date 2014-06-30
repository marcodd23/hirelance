package it.mwt.hirelance.presentation;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.FilterDataRequest;
import it.mwt.hirelance.business.FilterDataResponse;
import it.mwt.hirelance.business.InboxService;
import it.mwt.hirelance.business.ProjectService;
import it.mwt.hirelance.business.WorkRoomService;
import it.mwt.hirelance.business.model.FeedBack;
import it.mwt.hirelance.business.model.Message;
import it.mwt.hirelance.business.model.Proposal;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.common.spring.security.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/workroom")
public class WorkroomController {

	@Autowired
	private WorkRoomService wrService;

	@Autowired
	private InboxService inboxService;
	
	@Autowired
	private ProjectService projectService;

	@RequestMapping("/create")
	public String workRoomCreate(@RequestParam("proposalID") int proposalID,
			Model model) throws BusinessException {

		int projectID = wrService.createWorkRoom(proposalID);

		return "redirect:/workroom/views?projectID=" + projectID;
	}

	@RequestMapping("/views")
	public String workRoomViews(Model model,@RequestParam("projectID") int projectID) throws BusinessException {

		UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userLogged = userDetail.getUser();
		System.out.println("Sono l'utente "+userLogged.toString());
		model.addAttribute("project", projectService.findProjectByID(projectID));
		model.addAttribute("proposalID",wrService.findActiveWorkRoom(projectID, userLogged).getItems().get(0).getProposalID());
		model.addAttribute("filterDataRequest", new FilterDataRequest());
		model.addAttribute("feedback", new FeedBack());
		return "workroom.views";

	}

	@RequestMapping("/views/jobresume")
	public @ResponseBody
	FilterDataResponse<Proposal> workRoomJobResume(@ModelAttribute FilterDataRequest filterDataRequest)throws BusinessException {
		UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userLogged = userDetail.getUser();
		return wrService.findActiveWorkRoom(filterDataRequest.getProjectID(), userLogged);
	}

	@RequestMapping("/messages/findAllWorkroomMessages")
	public @ResponseBody
	FilterDataResponse<Message> allWorkRoomMessages(
			@ModelAttribute FilterDataRequest filterDataRequest)
			throws BusinessException {

		return inboxService.findAllInboxMessages(filterDataRequest);
	}

	@RequestMapping("/message/send")
	public @ResponseBody
	boolean sendMessage(@RequestParam("messageText") String messageText,
			@RequestParam("userID") int senderID,
			@RequestParam("proposalID") int proposalID)
			throws BusinessException {

		try {
			inboxService.sendMessage(messageText, senderID, proposalID);
			return true;
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

}
