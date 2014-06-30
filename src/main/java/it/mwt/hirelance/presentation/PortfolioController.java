package it.mwt.hirelance.presentation;

import java.util.ArrayList;
import java.util.List;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.PortfolioService;
import it.mwt.hirelance.business.UserService;
import it.mwt.hirelance.business.model.PortfolioItem;
import it.mwt.hirelance.business.model.UploadedFile;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.common.spring.security.UserDetailsImpl;
import it.mwt.hirelance.common.util.UploadUtility;
import it.mwt.hirelance.presentation.validator.ImageValidator;
import it.mwt.hirelance.presentation.validator.PortfolioValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/portfolio")
public class PortfolioController {
	
	@Autowired
	private PortfolioService service;
	
	@Autowired
	private UserService uService;

	@Autowired
	private ImageValidator imageValidator;
	
	@Autowired
	private PortfolioValidator portfolioValidator;

		
	@RequestMapping("/views")
	public String viewPortfolio(Model model,@RequestParam("userID") int userID) throws BusinessException{
		User u=uService.findUserById(userID);
		model.addAttribute("user",u);
		return "portfolio.views";
	}
	
	@RequestMapping("/showMoreElements")
	public @ResponseBody List<PortfolioItem> showMoreElements(@RequestParam("freelanceID") int freelanceID) throws BusinessException{
		return service.findMorePortfolioItems(freelanceID);
	}
	
	@RequestMapping("/delete")
	public String deletePortfolioItem(@RequestParam("portfolioItemID") int portfolioItemID) throws BusinessException{
		UserDetailsImpl userLogged = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userLogged.getUser();
		service.deletePortfolioItem(portfolioItemID,u);
		return "redirect:/profiles/freelancer/views?userID="+u.getUserID();
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String createPortfolioStart(Model model){
		model.addAttribute("portfolioItem", new PortfolioItem());
		return "portfolio.createform";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String createPortfolio(
			@ModelAttribute PortfolioItem portfolioItem, 
			@RequestParam(value="imagefile", required=false) MultipartFile file,
			BindingResult bindingResult,
			Model model) throws BusinessException{
		
		List<String> errorMsg = new ArrayList<String>();
		UploadedFile image = null;
		
	    //trovo l'user loggato  
		UserDetailsImpl userLogged = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userLogged.getUser();
		
		portfolioValidator.validate(portfolioItem, bindingResult);	
		if (!file.isEmpty()) {
			imageValidator.validate(file, errorMsg);
		}	
		if(bindingResult.hasErrors() | !errorMsg.isEmpty()){
			if(!errorMsg.isEmpty()){
				model.addAttribute("imageErrors", errorMsg);
			}

			return "portfolio.createform";
		 }

		if (!file.isEmpty()) {	
			image = UploadUtility.setupFileToUpload(file);     
		}
		portfolioItem.setPortfolioFile(image);
		service.create(portfolioItem,user);
		//user.getFreelanceProfile().addPortfolioItem(portfolioItem);
		//uService.update(user);
		
		return "redirect:/profiles/freelancer/views?userID="+user.getUserID();
	}
}
