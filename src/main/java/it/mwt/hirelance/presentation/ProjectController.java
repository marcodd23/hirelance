package it.mwt.hirelance.presentation;

import java.math.BigInteger;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.CategoryService;
import it.mwt.hirelance.business.FilterDataRequest;
import it.mwt.hirelance.business.FilterDataResponse;
import it.mwt.hirelance.business.ProfileService;
import it.mwt.hirelance.business.ProjectService;
import it.mwt.hirelance.business.RequestGrid;
import it.mwt.hirelance.business.ResponseGrid;
import it.mwt.hirelance.business.UserService;
import it.mwt.hirelance.business.model.Category;
import it.mwt.hirelance.business.model.ClientProfile;
import it.mwt.hirelance.business.model.FeedBack;
import it.mwt.hirelance.business.model.FreelanceProfile;
import it.mwt.hirelance.business.model.Message;
import it.mwt.hirelance.business.model.Project;
import it.mwt.hirelance.business.model.Project.ProjectStatus;
import it.mwt.hirelance.business.model.Proposal;
import it.mwt.hirelance.business.model.Proposal.ProposalStatus;
import it.mwt.hirelance.business.model.Skill;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.common.spring.security.UserDetailsImpl;
import it.mwt.hirelance.common.util.MyCustomNumberEditor;
import it.mwt.hirelance.common.util.SkillCollectionEditor;
import it.mwt.hirelance.presentation.validator.ProjectValidator;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/projects")
public class ProjectController {

	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private CategoryService cService;
	
	@Autowired
	private ProfileService profilesService;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private ProjectValidator pValidator;
	
	@InitBinder
    public void initBinder(WebDataBinder binder){
    	binder.registerCustomEditor(Collection.class, "skills", new SkillCollectionEditor(Collection.class));
    	binder.registerCustomEditor(BigInteger.class, "budgetMIN", new MyCustomNumberEditor(BigInteger.class, true));
    	binder.registerCustomEditor(BigInteger.class, "budgetMAX", new MyCustomNumberEditor(BigInteger.class, true));
    }
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String projectCreateStart(Model model) throws BusinessException{
		model.addAttribute("project", new Project());
		return "projects.createform";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String projectCreate(@ModelAttribute Project project, @RequestParam("userID") int userID, 
			@RequestParam("daysToPost") int daysToPost,BindingResult bindingResult, Model model, @RequestParam("projectMainCategory") int projectMainCategory) throws BusinessException{

		User u = uService.findUserById(userID);
		Map<String, String> errorMsg = new HashMap<String, String>(); 
		
		pValidator.validateProject(project, bindingResult, projectMainCategory, errorMsg);
		if(bindingResult.hasErrors() || !errorMsg.isEmpty()){
			model.addAttribute("mainCategorySelectError", errorMsg.get("mainCategorySelectError"));
			return "projects.createform";
		}
		ClientProfile client = u.getClientProfile();
		projectService.createProject(project, client, daysToPost);
		//Aggiungo all'utente in sessione
		UserDetailsImpl a = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		a.getUser().setClientProfile(client);
		
		return "redirect:/projects/views?projectID="+project.getProjectID();
	}
	
	@RequestMapping("/views")
	public String projectViews(Model model, @RequestParam("projectID") int projectID) throws BusinessException{
		Project project = projectService.findProjectByID(projectID);			
		User user = uService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		  if(user!=null){ 
		    boolean alreadyCandidate = projectService.checkAlreadyPostProposal(user, project);
		    model.addAttribute("alreadyCandidate", alreadyCandidate);
		}
		model.addAttribute("filterDataRequest",new FilterDataRequest());
		model.addAttribute("project", project);
		model.addAttribute("projectStatus", project.getStatus().toString());	
		model.addAttribute("hiringClosed", (project.getStatus().equals(ProjectStatus.HIRING_CLOSED) || project.getStatus().equals(ProjectStatus.EXPIRED)));
		return "projects.views";
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	
	public String projectUpdateStart(@RequestParam("projectID") int projectID, Model model) throws BusinessException{
		Project project = projectService.findProjectByID(projectID);
/*		List<MainCategory> mainCategories = cService.findAllMainCategories();
		model.addAttribute("categories", mainCategories);*/
		model.addAttribute("project", project);
		UserDetailsImpl userLogged = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userLogged.getUser();
		if(project.getClientOwner().getUser().getUserID()==u.getUserID())
			return "projects.updateform";
		else
			return "redirect:/access_denied";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String projectUpdate(@ModelAttribute Project project, @RequestParam("daysToPost") int daysToPost,
			@RequestParam("userID") int userID, BindingResult bindingResult, Model model) throws BusinessException{
		
		User u = uService.findUserById(userID);
		pValidator.validate(project, bindingResult);
		if(bindingResult.hasErrors()){
			return "projects.updateform";
		}
		ClientProfile client = u.getClientProfile();
		project.setStatus(ProjectStatus.HIRING_OPEN);
		projectService.projectUpdate(project, daysToPost, client);
		
		//aggiungo all'utente in sessione
		UserDetailsImpl a = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		a.getUser().setClientProfile(client);
		return "redirect:/projects/views?projectID="+project.getProjectID();
	}
	
	@RequestMapping(value="/sub/populateDependentSelects", method = RequestMethod.GET)
	public @ResponseBody FilterDataResponse<Skill> dependentSelectUpdate(@RequestParam(value="mainID", required=true) String mainID, 
			@RequestParam(value="projectID", required=false) String projectID) throws BusinessException{
		return cService.findAllSubCatAndSkillsByMainID(mainID, projectID);
	}
	
	@RequestMapping(value = "/new_proposal", method = RequestMethod.GET )
	public String proposalCreateStart(Model model, @RequestParam("projectID") int projectID) throws BusinessException{
		
		model.addAttribute("proposal", new Proposal());
		model.addAttribute("projectID", projectID);
		return "proposal.createform";
	}
	
	
	@RequestMapping(value = "/new_proposal", method = RequestMethod.POST )
	public String proposalCreate(@ModelAttribute Proposal proposal, @RequestParam("projectID") int projectID, 
			@RequestParam("userID") int userID) throws BusinessException{
		    
        projectService.createProposal(proposal,projectID, userID);
        return "redirect:/projects/views?projectID="+projectID;
	}
	
	@RequestMapping("/proposals/findAllProposalsFiltered")
	public @ResponseBody FilterDataResponse<Proposal> findAllProposalFiltered(@ModelAttribute FilterDataRequest filterDataRequest) throws BusinessException{	
		return projectService.findAllProposalsFiltered(filterDataRequest);
	}
	
	@RequestMapping("/views_all")
	public String projectViewAll(Model model) throws BusinessException{
//		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("admin")){
//			return "ciao ciao";
//		}
		FilterDataRequest filterDataRequest = new FilterDataRequest();
		model.addAttribute("filterDataRequest",filterDataRequest);
		model.addAttribute("skills", cService.findAllSkills());
		model.addAttribute("title","p");
		model.addAttribute("feedback",new FeedBack());
		
		
		if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")){
			System.out.println("sono loggato");
			UserDetailsImpl userLogged = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = userLogged.getUser();
			if (user.getRole().getName().equals("admin")){
				System.out.println("sono admin");
				return("projects.views_all_admin");
			}
			else{
				System.out.println("sono user");
				return "projects.views_all";				
			}
		}
		else{
			System.out.println("non sono loggato");
			return "projects.views_all";

		}
	}
	
	@RequestMapping("/findAllProjectsPaginated")
	@ResponseBody
	public ResponseGrid<Project> findAllUsersPaginated(@ModelAttribute RequestGrid requestGrid) throws BusinessException{
		ResponseGrid<Project> result= projectService.findAllProjectPaginated(requestGrid);
		return result;
	}
	
	@RequestMapping("/findAllProjectsFiltered")
	public @ResponseBody FilterDataResponse<Project> findAllProjectsFiltered(@ModelAttribute FilterDataRequest filterDataRequest) throws BusinessException{
		//System.out.println(filterDataRequest.toString());
		return projectService.findAllProjectsFiltered(filterDataRequest);
	}
	
	@RequestMapping("/findAllClientProjectsFiltered")
	public @ResponseBody FilterDataResponse<Project> findAllClientProjectsFiltered(@ModelAttribute FilterDataRequest filterDataRequest) throws BusinessException{
		//System.out.println(filterDataRequest.toString());
		return projectService.findAllClientProjectsFiltered(filterDataRequest);
	}
	
	@RequestMapping("/findAllFreelanceProjectsFiltered")
	public @ResponseBody FilterDataResponse<Project> findAllFreelanceProjectsFiltered(@ModelAttribute FilterDataRequest filterDataRequest) throws BusinessException{
		//System.out.println(filterDataRequest.toString());
		return projectService.findAllFreelanceProjectsFiltered(filterDataRequest);
	}
	
	@RequestMapping("/my_projects")
	public String myProjects(Model model) throws BusinessException{
		FilterDataRequest filterDataRequest = new FilterDataRequest();
		model.addAttribute("filterDataRequest",filterDataRequest);
		model.addAttribute("skills", cService.findAllSkills());
		model.addAttribute("feedback",new FeedBack());
		return "projects.my";
	}
	
	@RequestMapping(value="/addFeedback",method=RequestMethod.POST)
	public @ResponseBody void addFeedback(@ModelAttribute("feedback") FeedBack feedback) throws BusinessException{
		int projectID=0;
		
		projectService.addFeedback(feedback, projectID);
	}

	@ModelAttribute
	public void findAllMainCategories(Model model) throws BusinessException{
		List<Category> mainCategories = cService.findAllMainCategories();
		model.addAttribute("categories", mainCategories);
		
	}
}
