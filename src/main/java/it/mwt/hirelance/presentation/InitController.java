package it.mwt.hirelance.presentation;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.InitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/init")
public class InitController {
	
	@Autowired
	private InitService service;
	
	@RequestMapping("/populate")
	public String populate() throws BusinessException{
		service.populate();	
		return ("redirect:/");
	}

}
