package it.mwt.hirelance.presentation;

import java.util.List;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.CategoryService;
import it.mwt.hirelance.business.CurriculumService;
import it.mwt.hirelance.business.model.Curriculum;
import it.mwt.hirelance.business.model.Education;
import it.mwt.hirelance.business.model.Employment;
import it.mwt.hirelance.business.model.Language;
import it.mwt.hirelance.business.model.Skill;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.common.spring.security.UserDetailsImpl;
import it.mwt.hirelance.presentation.validator.EducationValidator;
import it.mwt.hirelance.presentation.validator.EmploymentValidator;
import it.mwt.hirelance.presentation.validator.LanguageValidator;

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

@Controller
@RequestMapping("/curriculum")
public class CurriculumController {

	@Autowired
	private CurriculumService service;
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private EducationValidator eduValidator;
	
	@Autowired
	private EmploymentValidator emplValidator;

	@Autowired
	private LanguageValidator langValidator;
	
	
	@RequestMapping(value="/aboutme/create", method=RequestMethod.POST)
	public String createAboutMe(@RequestParam("aboutMe") String aboutMe, @RequestParam("curriculumID") int curriculumID, @RequestParam("userID") int userID) throws BusinessException{
		Curriculum curriculum = service.findCurriculumById(curriculumID);
		curriculum.setAboutMe(aboutMe);
		service.updateCurriculum(curriculum);
		return "redirect:/profiles/freelancer/views?userID="+userID;
	}
	
	@RequestMapping(value="/educations/create", method=RequestMethod.GET)
	@PreAuthorize("#curriculumID==principal.user.freelanceProfile.curriculum.curriculumID")
	public String createEducationStart(Model model, @RequestParam("curriculumID") int curriculumID){
		model.addAttribute("education", new Education());
		model.addAttribute("curriculumID", curriculumID);
		return "educations.createform";
	}	
	
	@RequestMapping(value="/educations/create", method=RequestMethod.POST)
	public String createEducation(@ModelAttribute Education education, BindingResult bindingResult) throws BusinessException{
		eduValidator.validate(education, bindingResult);
		if(bindingResult.hasErrors())
			return "educations.createform";
		UserDetailsImpl userDetailsImpl=(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userDetailsImpl.getUser();
		int curriculumID=u.getFreelanceProfile().getCurriculum().getCurriculumID();
		service.createEducation(education);
		Curriculum c = service.findCurriculumById(curriculumID);
		c.addEducation(education);
		service.updateCurriculum(c);
		
		return "redirect:/profiles/freelancer/views?userID="+u.getUserID();
	}
	
	@RequestMapping(value="/educations/update", method=RequestMethod.GET)
	public String updateEducationStart(Model model, @RequestParam("educationID") int educationID) throws BusinessException{
		Education education=service.findEducationById(educationID);
		model.addAttribute("education",education );
		return "educations.updateform";
	}
	
	@RequestMapping(value="/educations/update", method=RequestMethod.POST)
	public String updateEducation(@ModelAttribute Education education, BindingResult bindingResult) throws BusinessException{
		eduValidator.validate(education, bindingResult);
		if(bindingResult.hasErrors())
			return "educations.updateform";
		service.updateEducation(education);
		UserDetailsImpl userDetailsImpl=(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userDetailsImpl.getUser();
		return "redirect:/profiles/freelancer/views?userID="+u.getUserID();
	}
	
	@RequestMapping("/educations/delete")
	public String deleteEducation(@RequestParam("educationID") int educationID) throws BusinessException{
		service.deleteEducation(service.findEducationById(educationID));
		UserDetailsImpl userDetailsImpl=(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userDetailsImpl.getUser();
		return "redirect:/profiles/freelancer/views?userID="+u.getUserID();
	}
	
	
	
	@RequestMapping(value="/employments/create", method=RequestMethod.GET)
	@PreAuthorize("#curriculumID==principal.user.freelanceProfile.curriculum.curriculumID")
	public String createEmploymentStart(Model model, @RequestParam("curriculumID") int curriculumID){
		model.addAttribute("employment", new Employment());
		model.addAttribute("curriculumID", curriculumID);
		return "employments.createform";
	}
	
	@RequestMapping(value="/employments/create", method=RequestMethod.POST)
	public String createEmployment(@ModelAttribute Employment employment,BindingResult bindingResult) throws BusinessException{
		emplValidator.validate(employment, bindingResult);
		if(bindingResult.hasErrors())
			return "employments.createform";
		UserDetailsImpl userDetailsImpl=(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userDetailsImpl.getUser();
		int curriculumID=u.getFreelanceProfile().getCurriculum().getCurriculumID();
		service.createEmployment(employment);
		Curriculum c = service.findCurriculumById(curriculumID);
		c.addEmployment(employment);
		service.updateCurriculum(c);
		
		return "redirect:/profiles/freelancer/views?userID="+u.getUserID();
	}
	
	@RequestMapping(value="/employments/update", method=RequestMethod.GET)
	public String updateEmploymentStart(Model model, @RequestParam("employmentID") int employmentID) throws BusinessException{
		Employment employment=service.findEmploymentById(employmentID);
		model.addAttribute("employment",employment );
		return "employments.updateform";
	}
	
	@RequestMapping(value="/employments/update", method=RequestMethod.POST)
	public String updateEmployment(@ModelAttribute Employment employment, BindingResult bindingResult) throws BusinessException{
		emplValidator.validate(employment, bindingResult);
		if(bindingResult.hasErrors())
			return "employments.updateform";
		service.updateEmployments(employment);
		UserDetailsImpl userDetailsImpl=(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userDetailsImpl.getUser();
		return "redirect:/profiles/freelancer/views?userID="+u.getUserID();
	}
	
	@RequestMapping("/employments/delete")
	public String deleteEmployment(@RequestParam("employmentID") int employmentID) throws BusinessException{
		service.deleteEmployment(service.findEmploymentById(employmentID));
		UserDetailsImpl userDetailsImpl=(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userDetailsImpl.getUser();
		return "redirect:/profiles/freelancer/views?userID="+u.getUserID();
	}
	
	
	@RequestMapping(value="/languages/create", method=RequestMethod.GET)
	@PreAuthorize("#curriculumID==principal.user.freelanceProfile.curriculum.curriculumID")
	public String createLanguageStart(Model model, @RequestParam("curriculumID") int curriculumID){
		model.addAttribute("language", new Language());
		model.addAttribute("curriculumID", curriculumID);
		return "languages.createform";
	}
	
	@RequestMapping(value="/languages/create", method=RequestMethod.POST)
	public String createLanguage(@ModelAttribute Language language,BindingResult bindingResult) throws BusinessException{
		langValidator.validate(language, bindingResult);
		if(bindingResult.hasErrors())
			return "languages.createform";
		UserDetailsImpl userDetailsImpl=(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userDetailsImpl.getUser();
		int curriculumID=u.getFreelanceProfile().getCurriculum().getCurriculumID();
		service.createLanguage(language);
		Curriculum c = service.findCurriculumById(curriculumID);
		c.addLanguage(language);
		service.updateCurriculum(c);
		
		return "redirect:/profiles/freelancer/views?userID="+u.getUserID();
	}
	
	@RequestMapping(value="/languages/update", method=RequestMethod.GET)
	public String updateLanguageStart(Model model, @RequestParam("langID") int langID) throws BusinessException{
		Language language=service.findLanguageById(langID);
		model.addAttribute("language",language );
		return "languages.updateform";
	}
	
	@RequestMapping(value="/languages/update", method=RequestMethod.POST)
	public String updateLanguage(@ModelAttribute Language language, BindingResult bindingResult) throws BusinessException{
		langValidator.validate(language, bindingResult);
		if(bindingResult.hasErrors())
			return "languages.updateform";
		service.updateLanguage(language);
		UserDetailsImpl userDetailsImpl=(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userDetailsImpl.getUser();
		return "redirect:/profiles/freelancer/views?userID="+u.getUserID();
	}
	
	@RequestMapping("/languages/delete")
	public String deleteLanguage(@RequestParam("langID") int langID) throws BusinessException{
		service.deleteLanguage(service.findLanguageById(langID));
		UserDetailsImpl userDetailsImpl=(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userDetailsImpl.getUser();
		return "redirect:/profiles/freelancer/views?userID="+u.getUserID();
	}
	
	@RequestMapping(value="/skills/views", method=RequestMethod.GET)
	@PreAuthorize("#curriculumID==principal.user.freelanceProfile.curriculum.curriculumID")
	public String viewSkills(Model model, @RequestParam("curriculumID") int curriculumID) throws BusinessException{
		Curriculum c = service.findCurriculumById(curriculumID);
		model.addAttribute("curriculum", c);
		List<Skill> skills = catService.findAllSkills();
		String skillArray="";
		for (Skill skill : skills) {
			skillArray+="{label:\""+skill.getName()+" ("+skill.getMainCategory().getName()+")\",value:\""+skill.getName()+"\"},";
		}
		model.addAttribute("skillArray", skillArray);
		return "skills.views";
	}
	
	@RequestMapping(value="/skills/add", method=RequestMethod.GET)
	@PreAuthorize("#curriculumID==principal.user.freelanceProfile.curriculum.curriculumID")
	public String addSkill(@RequestParam("curriculumID") int curriculumID, @RequestParam("name") String name) throws BusinessException{
		Curriculum c = service.findCurriculumById(curriculumID);
		Skill s = catService.findSkillByName(name);
		service.addSkill(c, s);
		return("redirect:/curriculum/skills/views?curriculumID="+curriculumID);
	}
	
	@RequestMapping(value="/skills/delete", method=RequestMethod.GET)
	@PreAuthorize("#curriculumID==principal.user.freelanceProfile.curriculum.curriculumID")
	public String deleteSkill(@RequestParam("curriculumID") int curriculumID, @RequestParam("skillID") int skillID) throws BusinessException{
		Curriculum c = service.findCurriculumById(curriculumID);
		service.removeSkill(c,skillID);
		return("redirect:/curriculum/skills/views?curriculumID="+curriculumID);
	}
	
	
}
