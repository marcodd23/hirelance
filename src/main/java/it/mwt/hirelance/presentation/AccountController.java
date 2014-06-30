package it.mwt.hirelance.presentation;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.UserService;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.common.spring.security.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService service;

	@RequestMapping("/views")
	public String views(Model model) throws BusinessException{
		User u = service.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("fp", u.getFreelanceProfile());
		model.addAttribute("cp", u.getClientProfile());
		return "account.views";
	}
	
	@RequestMapping("/delete")
	public String delete() throws BusinessException{
		UserDetailsImpl u= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		service.delete(u.getUser());
		return "redirect:/j_spring_security_logout";
	}
}
