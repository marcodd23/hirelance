package it.mwt.hirelance.presentation;

import java.util.ArrayList;
import java.util.List;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.CategoryService;
import it.mwt.hirelance.business.FilterDataRequest;
import it.mwt.hirelance.business.FilterDataResponse;
import it.mwt.hirelance.business.ProfileService;
import it.mwt.hirelance.business.ProjectService;
import it.mwt.hirelance.business.UserService;
import it.mwt.hirelance.business.model.Category;
import it.mwt.hirelance.business.model.ClientProfile;
import it.mwt.hirelance.business.model.Curriculum;
import it.mwt.hirelance.business.model.FeedBack;
import it.mwt.hirelance.business.model.FreelanceProfile;
import it.mwt.hirelance.business.model.Proposal;
import it.mwt.hirelance.business.model.UploadedFile;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.common.spring.security.UserDetailsImpl;
import it.mwt.hirelance.common.util.UploadUtility;
import it.mwt.hirelance.presentation.validator.ClientProfileValidator;
import it.mwt.hirelance.presentation.validator.FreelanceProfileValidator;
import it.mwt.hirelance.presentation.validator.ImageValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/profiles")
public class ProfilesController {

	@Autowired
	private CategoryService cService;
	@Autowired
	private UserService uService;
	@Autowired
	private ProfileService pService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private FreelanceProfileValidator freelanceValidator;
	@Autowired
	private ClientProfileValidator clientValidator;
	
	@Autowired
	private ImageValidator imageValidator;
	
	//@Value("#{uploadBasePath['filesystem.path']}")
/*	@Value("#{uploadBasePath.filesystem}")
	private String basePath;*/
	
	@RequestMapping(value="/freelancer/create",method=RequestMethod.GET)
	//@PreAuthorize("hasRole('admin')")
	public String freelancerCreateStart(Model model) throws BusinessException{
		model.addAttribute("freelanceProfile", new FreelanceProfile());
		return "freelancers.createform";
	}
	
	@RequestMapping(value="/freelancer/create", method=RequestMethod.POST)
	public String freelancerCreate(@ModelAttribute FreelanceProfile freelanceProfile,
			@RequestParam(value="imagefile", required=false) MultipartFile file,BindingResult bindingResult,Model model) throws BusinessException{
		
		List<String> errorMsg = new ArrayList<String>();
		UploadedFile image = null;
		
/*		//trovo l'user loggato  */    
		UserDetailsImpl userLogged = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userLogged.getUser();
		freelanceValidator.validate(freelanceProfile, bindingResult);	
		if (!file.isEmpty()) {
			imageValidator.validate(file, errorMsg);
		}	
		if(bindingResult.hasErrors() | !errorMsg.isEmpty()){
			if(!errorMsg.isEmpty()){
				model.addAttribute("imageErrors", errorMsg);
			}

			return "freelancers.createform";
		 }
		
		if (!file.isEmpty()) {	
			image = UploadUtility.setupFileToUpload(file);
		}
		
		freelanceProfile.setCurriculum(new Curriculum());
		freelanceProfile.setImage(image);
		user = pService.createFreelancerProfile(freelanceProfile, user);
		
		//aggiungo il profilo da clients all'utente in sessione
		((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal())
		     .getUser().setFreelanceProfile(user.getFreelanceProfile());
		
		return "redirect:/profiles/freelancer/views?userID="+user.getUserID();
		
	}
	
	@RequestMapping(value="/freelancer/update",method=RequestMethod.GET)
	@PreAuthorize("#userID==principal.user.userID")
	//@PreAuthorize("1==2")
	public String freelancerUpdateStart(@RequestParam("userID") int userID, Model model) throws BusinessException{
		model.addAttribute("freelanceProfile", uService.findFreelancerUser(userID).getFreelanceProfile());
		return "freelancers.updateform";
	}
	
	@RequestMapping(value = "/freelancer/update", method = RequestMethod.POST)
	public String freelanceUpdate(@ModelAttribute FreelanceProfile freelanceProfile, @RequestParam(value="imagefile", required=false) MultipartFile file, BindingResult bindingResult, Model model) throws BusinessException {
		
		List<String> errorMsg = new ArrayList<String>();
		UploadedFile image = null;
		
		//trovo l'utente loggato
		UserDetailsImpl userLogged = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userLogged.getUser();
		
		freelanceValidator.validate(freelanceProfile, bindingResult);
		if (!file.isEmpty()) {
			imageValidator.validate(file, errorMsg);
		}
		if(bindingResult.hasErrors() || !errorMsg.isEmpty()){
			model.addAttribute("imageErrors", errorMsg);
			return "freelancers.updateform";
		 }
	
		if(!file.isEmpty()){	
			//UploadedFile image = UploadUtility.upload(file, u, "/client/images/");
			image = UploadUtility.setupFileToUpload(file);
		}
		System.out.println(freelanceProfile.getFreelanceName());
		pService.updateFreelancerProfile(freelanceProfile, user, image);

		return "redirect:/profiles/freelancer/views?userID="+user.getUserID();
		}
	
	@RequestMapping("/freelancer/views")
	public String freelancerViews(Model model,@RequestParam("userID") int userID) throws BusinessException{
		model.addAttribute("user", uService.findFreelancerUser(userID));
		List<FeedBack> feedbacks = uService.findAllFeedback(userID);
		System.out.println("I feedback sono: "+feedbacks.size());
		model.addAttribute("feedbacks", feedbacks);
		
		return "freelancers.views";
	}
	
	@RequestMapping("/freelancer/views_all")
	public String freelancerViewAll(Model model) throws BusinessException{
		FilterDataRequest filterDataRequest = new FilterDataRequest();
		model.addAttribute("filterDataRequest",filterDataRequest);
		model.addAttribute("skills", cService.findAllSkills());
		model.addAttribute("title","f");
		model.addAttribute("feedback",new FeedBack());

		return "freelancers.views_all";
	}
	
	@RequestMapping("/freelancer/delete")
	public String freelancerDelete() throws BusinessException{
		UserDetailsImpl u= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//pService.deleteFreelanceProfile(u.getUser().getFreelanceProfile());
		uService.removeFreelancerProfile(u.getUser());
		return "redirect:/account/views";
	}

	@RequestMapping("/freelancer/addProjectToWatchList")
	public @ResponseBody void addProject(@RequestParam("projectID") int projectID) throws BusinessException{
		UserDetailsImpl userLogged = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		FreelanceProfile fp = userLogged.getUser().getFreelanceProfile();
		pService.addProjectToWatchList(fp,projectService.findProjectByID(projectID));
	}
	
	@RequestMapping("/freelancer/removeProjectToWatchList")
	public @ResponseBody void removeProject(@RequestParam("projectID") int projectID) throws BusinessException{
		UserDetailsImpl userLogged = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		FreelanceProfile fp = userLogged.getUser().getFreelanceProfile();
		pService.removeProjectToWatchList(fp,projectService.findProjectByID(projectID));
		//return "redirect:/profiles/freelancer/views?userID="+userLogged.getUser().getUserID();
		return;
	}
	

	@RequestMapping("/client/delete")
	public String clientDelete() throws BusinessException{
		UserDetailsImpl u= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		uService.removeClientProfile(u.getUser());
		return "redirect:/account/views";
	}
	
	@RequestMapping("/freelancer/findAllFreelancersFiltered")
	public @ResponseBody FilterDataResponse<User> findAllFreelancersFiltered(@ModelAttribute FilterDataRequest filterDataRequest) throws BusinessException{
		//System.out.println(filterDataRequest.toString());
		return uService.findAllFreelancersFiltered(filterDataRequest);
	}
	
	@RequestMapping(value="/client/create",method=RequestMethod.GET)
	public String clientCreateStart(Model model) throws BusinessException{
		//List<MainCategory> mainCategories = cService.findAllMainCategories();
		//model.addAttribute("mainCategories", mainCategories);
		model.addAttribute("clientProfile", new ClientProfile());
		return "clients.createform";
	}

	
	
	@RequestMapping(value="/client/create", method=RequestMethod.POST)
	public String clientCreate(@ModelAttribute ClientProfile clientProfile, @RequestParam(value="imagefile", required=false) MultipartFile file, BindingResult bindingResult, Model model) throws BusinessException{
		
		List<String> errorMsg = new ArrayList<String>();
		UploadedFile image = null;
		
/*		//trovo l'user loggato  */    
		UserDetailsImpl userLogged = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userLogged.getUser();
		
		clientValidator.validate(clientProfile, bindingResult);
		if (!file.isEmpty()) {
			imageValidator.validate(file, errorMsg);
		}
		if(bindingResult.hasErrors() || !errorMsg.isEmpty()){
			model.addAttribute("imageErrors", errorMsg);
			return "clients.createform";
		 }
/*		if(bindingResult.hasErrors() | !errorMsg.isEmpty()){
			if(!errorMsg.isEmpty()){
				model.addAttribute("imageErrors", errorMsg);
			}
			return "clients.createform";
		 }*/
		
		if(bindingResult.hasErrors() || !errorMsg.isEmpty()){
			model.addAttribute("imageErrors", errorMsg);
			return "clients.createform";
		 }
		if (!file.isEmpty()) {	
			image = UploadUtility.setupFileToUpload(file);
		}

        clientProfile.setImage(image);
		user = pService.createClientProfile(clientProfile, user);
		
		//aggiungo il profilo da clients all'utente in sessione
		((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal())
		.getUser().setClientProfile(user.getClientProfile());
		
		return "redirect:/profiles/client/views?userID="+user.getUserID();
		
	}

	
	@RequestMapping("/client/views")
	public String clientViews(Model model,@RequestParam("userID") int userID) throws BusinessException {
		User u = uService.findClientUser(userID);
		model.addAttribute("user", u);
		FilterDataRequest filterDataRequest = new FilterDataRequest();
		filterDataRequest.setUserID(userID);
		model.addAttribute("filterDataRequest",filterDataRequest);
		return "clients.views";
	}
	
	@RequestMapping("/client/findAllProposalFiltered")
	public @ResponseBody FilterDataResponse<Proposal> findAllProposalFiltered(@ModelAttribute FilterDataRequest filterDataRequest) 
			throws BusinessException{
		return pService.findAllProposalFiltered(filterDataRequest);
		
	}
	
	@RequestMapping("/client/views_all")
	public String clientViewAll(Model model) throws BusinessException{
		FilterDataRequest filterDataRequest = new FilterDataRequest();
		model.addAttribute("filterDataRequest",filterDataRequest);
		model.addAttribute("skills", cService.findAllSkills());
		model.addAttribute("title","c");
		model.addAttribute("feedback",new FeedBack());
		return "clients.views_all";
	}
	
	@RequestMapping("/client/findAllClientsFiltered")
	public @ResponseBody FilterDataResponse<User> findAllClientsFiltered(@ModelAttribute FilterDataRequest filterDataRequest) throws BusinessException{
		return uService.findAllClientsFiltered(filterDataRequest);
	}
	
	@RequestMapping(value = "/client/update", method = RequestMethod.GET)
	@PreAuthorize("#userID==principal.user.userID")
	public String clientUpdateStart(@RequestParam("userID") int userID, Model model) throws BusinessException {
		User user = uService.findClientUser(userID);
		model.addAttribute("clientProfile", user.getClientProfile());
		return "clients.updateform";
	}
	
	
	@RequestMapping(value = "/client/update", method = RequestMethod.POST)
	public String clientUpdate(@ModelAttribute ClientProfile clientProfile, @RequestParam(value="imagefile", required=false) MultipartFile file, BindingResult bindingResult, Model model) throws BusinessException {
		
		List<String> errorMsg = new ArrayList<String>();
		UploadedFile image = null;
		
		//trovo l'utente loggato
		UserDetailsImpl userLogged = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userLogged.getUser();
		
		clientValidator.validate(clientProfile, bindingResult);
		if (!file.isEmpty()) {
			imageValidator.validate(file, errorMsg);
		}
		if(bindingResult.hasErrors() || !errorMsg.isEmpty()){
			model.addAttribute("imageErrors", errorMsg);
			return "clients.updateform";
		 }
/*		if(bindingResult.hasErrors() | !errorMsg.isEmpty()){
			if(!errorMsg.isEmpty()){
				model.addAttribute("imageErrors", errorMsg);
			}
			return "clients.createform";
		 }*/
	
		if(!file.isEmpty()){	
			//UploadedFile image = UploadUtility.upload(file, u, "/client/images/");
			image = UploadUtility.setupFileToUpload(file);
		}
		
		pService.updateClientProfile(clientProfile, user, image);

		//Aggiungo all'utente in sessione
		//userLogged.getUser().setClientProfile(clientProfile);
		
		return "redirect:/profiles/client/views?userID="+user.getUserID();
	}
	
	@ModelAttribute
	public void findAllMainCategories(Model model) throws BusinessException{
		List<Category> mainCategories = cService.findAllMainCategories();
		model.addAttribute("categories", mainCategories);
		
	}

}
