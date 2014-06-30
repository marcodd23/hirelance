package it.mwt.hirelance.presentation;


import java.util.List;
import java.util.Random;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.RequestGrid;
import it.mwt.hirelance.business.ResponseGrid;
import it.mwt.hirelance.business.UserService;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.common.spring.security.UserDetailsImpl;
import it.mwt.hirelance.common.util.MD5Hash;
import it.mwt.hirelance.presentation.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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


@Controller
@RequestMapping("/users")
public class UsersController {
	
	
	@Autowired
	private UserService service;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private UserValidator validator;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createStart(Model model) throws BusinessException {
		model.addAttribute("user", new User());
		return "users.createform";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createUser(@ModelAttribute User user, @RequestParam("confirmEmail") String confirmEmail, @RequestParam("confirmPassword") String confirmPassword, BindingResult bindingResult) throws BusinessException {
		
		boolean existUsername = service.existAlreadyUser(user.getUsername());
		boolean existEmail = service.existAlreadyEmail(user.getEmail());
		validator.validateForm(user, bindingResult, confirmEmail, confirmPassword, existUsername, existEmail);
		if(bindingResult.hasErrors())return "users.createform";
		service.create(user);
		String logged_username=SecurityContextHolder.getContext().getAuthentication().getName();
		if(logged_username.equals("anonymousUser")){
			//System.out.println("non sono autenticato");
			return "redirect:/login_ready";
		}
		else{
			//System.out.println("sono autenticato");
			//System.out.println("La mia username è "+logged_username);
			int logged_role = service.findUserByUsername(logged_username).getRole().getRoleID();
			//System.out.println("Il mio ruolo è "+logged_role);
			if(logged_role==1)
				return "redirect:/users/views";
			else
				return "redirect:/common/welcome";
		}
	}
	
	@RequestMapping("/views")
	public String views(){
		return "users.views";
	}
	
	@RequestMapping("/findAllUsersPaginated")
	@ResponseBody
	public ResponseGrid<User> findAllUsersPaginated(@ModelAttribute RequestGrid requestGrid) throws BusinessException{
		ResponseGrid<User> result= service.findAllUserPaginated(requestGrid);
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteStart(@RequestParam("user_id") int user_id, Model model) throws BusinessException {
		User user = service.findUserById(user_id);
		model.addAttribute("user", user);
		return "users.deleteform";
	}
    
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute User user) throws BusinessException {
		
	    service.delete(user);
		return "redirect:/users/views";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@PreAuthorize("hasRole('admin') OR principal.user.userID==#userID")
	public String updateStart(@RequestParam("user_id") int userID, Model model) throws BusinessException {
		User user = service.findUserById(userID);
		model.addAttribute("user", user);
		return "users.updateform";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute User user, BindingResult bindingResult) throws BusinessException {
		validator.validate(user, bindingResult);
		if(bindingResult.hasErrors()){
			return "users.updateform";
		}
		User userLogged = ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser(); 
		if(userLogged.getUserID()==user.getUserID()){
			user.setRole(userLogged.getRole());
			user.setCreationDate(userLogged.getCreationDate());
		}
	    service.update(user);
	    if(userLogged.getRole().getName().equals("admin"))
	    	return "redirect:/users/views";
	    else{
	    	//return "redirect:/account/views";
	    	return "redirect:/j_spring_security_logout";
	    }	    	
	}
	
	@RequestMapping(value="/retrivalCredentials",method=RequestMethod.POST)
	public String sendCredentials(@RequestParam("email") String userEmail) throws BusinessException{
		// takes input from e-mail form

        List<User> users = service.findUserByEmail(userEmail);
        
        if(users.isEmpty())return("redirect:/login_retrival_exception");
        else {
        	User u = users.get(0);
        	Random r = new Random();
        	String caratteri="abcdefghilmnopqrstuvz1234567890";
        	 char[] text = new char[8];
        	    for (int i = 0; i < 8; i++)
        	    {
        	        text[i] = caratteri.charAt(r.nextInt(caratteri.length()));
        	    }
        	String newPassword= new String(text);
        	u.setPassword(newPassword);
        	service.update(u);
        	SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(userEmail);
            email.setSubject("Le tue nuove credenziali HIRELANCE");
            email.setFrom("Hirelance");
            email.setText("Username: "+u.getUsername()+"\nPassword: "+newPassword+"\nSe non " +
            		"sei stato tu a fare questa richiesta controlla che non ti abbiamo fottuto la password");
            try{
    	        mailSender.send(email);
            }
            catch(Exception e){
            	return "redirect:/login_retrival_exception";
            }
            return "redirect:/login_retrival_ok";
        }
	}
}
