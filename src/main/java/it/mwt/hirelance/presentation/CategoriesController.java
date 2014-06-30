package it.mwt.hirelance.presentation;

import java.util.List;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.CategoryService;
import it.mwt.hirelance.business.RequestGrid;
import it.mwt.hirelance.business.ResponseGrid;
import it.mwt.hirelance.business.model.Category;
import it.mwt.hirelance.business.model.Skill;
import it.mwt.hirelance.presentation.validator.MainValidator;
import it.mwt.hirelance.presentation.validator.SkillValidator;
import it.mwt.hirelance.presentation.validator.SubValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/categories")
public class CategoriesController {
	
	@Autowired
	private CategoryService service;
	
	@Autowired
	private MainValidator mainValidator;
	@Autowired
	private SubValidator subValidator;
	@Autowired
	private SkillValidator skillValidator;
	
	@RequestMapping(value= "/main/create", method=RequestMethod.GET)
	public String mainCreateStart(Model model){
		model.addAttribute("mainCategory", new Category());
		return "main.createform";
	}
	@RequestMapping(value="/main/create", method=RequestMethod.POST)
	public String mainCreate(@ModelAttribute Category mainCategory, BindingResult bindingResult) throws BusinessException{
		mainValidator.validate(mainCategory,bindingResult);
		if(bindingResult.hasErrors())
			return "main.createform";
		service.create(mainCategory);
		return "redirect:/categories/main/views";
	}
	
	@RequestMapping("/main/views")
	public String mainViews(){
		return "main.views";
	}
	@RequestMapping("/main/findAllMainCategoriesPaginated")
	@ResponseBody
	public ResponseGrid<Category> findAllMainCategoriesPaginated(@ModelAttribute RequestGrid requestGrid) throws BusinessException{
		ResponseGrid<Category> result = service.findAllMainCategoriesPaginated(requestGrid);
		return result;
	}
	
	@RequestMapping(value = "/main/delete", method = RequestMethod.GET)
	public String mainDeleteStart(@RequestParam("id") int main_id, Model model) throws BusinessException {
		Category mainCategory = service.findMainById(main_id);
		model.addAttribute("mainCategory", mainCategory);
		return "main.deleteform";
	}
    
	@RequestMapping(value = "/main/delete", method = RequestMethod.POST)
	public String mainDelete(@ModelAttribute Category mainCategory) throws BusinessException {
		
	    service.delete(mainCategory);
		return "redirect:/categories/main/views";
	}
	
	@RequestMapping(value= "/sub/create", method=RequestMethod.GET)
	public String subCreateStart(Model model){
		model.addAttribute("subCategory", new Category());
		return "sub.createform";
	}
	@RequestMapping(value="/sub/create", method=RequestMethod.POST)
	public String subCreate(@ModelAttribute Category subCategory, BindingResult bindingResult) throws BusinessException{
		subValidator.validate(subCategory, bindingResult);
		if(bindingResult.hasErrors())
			return "sub.createform";
		service.create(subCategory);
		return "redirect:/categories/sub/views";
	}
	@RequestMapping("/sub/views")
	public String subViews(@RequestParam(value="main_id",required=false) String main_id, Model model){
		model.addAttribute("main_id", main_id);
		return "sub.views";
	}
	@RequestMapping("/sub/findAllSubCategoriesPaginated")
	@ResponseBody
	public ResponseGrid<Category> findAllSubCategoriesPaginated(@ModelAttribute RequestGrid requestGrid,@RequestParam(value="main_id",required=false) String main_id) throws BusinessException{
		ResponseGrid<Category> result = service.findAllSubCategoriesPaginated(requestGrid,main_id);
		return result;
	}
	
	@RequestMapping(value = "/sub/delete", method = RequestMethod.GET)
	public String subDeleteStart(@RequestParam("id") int sub_id, Model model) throws BusinessException {
		Category subCategory = service.findSubById(sub_id);
		model.addAttribute("subCategory", subCategory);
		return "sub.deleteform";
	}
    
	@RequestMapping(value = "/sub/delete", method = RequestMethod.POST)
	public String subDelete(@ModelAttribute Category subCategory) throws BusinessException {
		
	    service.delete(subCategory);
		return "redirect:/categories/sub/views";
	}
	
	@RequestMapping(value= "/skill/create", method=RequestMethod.GET)
	public String skillCreateStart(Model model){
		model.addAttribute("skill", new Skill());
		return "skill.createform";
	}
	
	@RequestMapping(value="/skill/create", method=RequestMethod.POST)
	public String skillCreate(@ModelAttribute Skill skill,BindingResult bindingResult) throws BusinessException{
		skillValidator.validate(skill, bindingResult);
		if(bindingResult.hasErrors())
			return "skill.createform";
		service.create(skill);
		return "redirect:/categories/skill/views";
	}
	@RequestMapping("/skill/views")
	public String skillViews(@RequestParam(value="main_id",required=false) String main_id, Model model){
		model.addAttribute("main_id", main_id);
		return "skill.views";
	}
	@RequestMapping("/skill/findAllSkillsPaginated")
	@ResponseBody
	public ResponseGrid<Skill> findAllSkillsPaginated(@ModelAttribute RequestGrid requestGrid,@RequestParam(value="main_id",required=false) String main_id) throws BusinessException{
		ResponseGrid<Skill> result = service.findAllSkillsPaginated(requestGrid,main_id);
		return result;
	}
	
/*	@RequestMapping(value="/sub/findAllSubDependentOnMain", method = RequestMethod.GET)
	public @ResponseBody List<SubCategory> projectFormDependentSelect(@RequestParam(value="mainID", required=true) String mainID) throws BusinessException{
		
		return service.findSubCategoriesOfMainCategory(mainID);
	}*/
	
	
	@ModelAttribute
	public void findAllMainCategories(Model model) throws BusinessException{
		List<Category> mainCategories = service.findAllMainCategories();
		model.addAttribute("mainCategories", mainCategories);
		
	}
}
