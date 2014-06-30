package it.mwt.hirelance.presentation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sendEmail")
public class ContactController {
	
	    @Autowired
	    private JavaMailSender mailSender;
	    
	    @RequestMapping(method = RequestMethod.POST)
	    public @ResponseBody String doSendEmail(HttpServletRequest request) {
	    	
	        // takes input from e-mail form
	        String from = request.getParameter("email");
	        String subject = request.getParameter("subject");
	        String message = request.getParameter("message");
	        String name= request.getParameter("name"); 

	        // creates a simple e-mail object
	        SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo("daniele.simonetti87@gmail.com");
	        email.setSubject("Hirelance request: " +subject);
	        email.setFrom(from);
	        email.setText("Name: "+name+"\nemail: "+from+"\n\n"+message);
	        // sends the e-mail
	        try{
		        mailSender.send(email);
	        }
	        catch(Exception e){
	        	return e.getMessage();
	        }
	        return "";
	    }
}
