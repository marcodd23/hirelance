package it.mwt.hirelance.presentation;

import javax.servlet.http.HttpServletRequest;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.BusinessException.ExceptionCause;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

@ControllerAdvice
public class ExceptionControllerAdvice {
   
	
	@ExceptionHandler(BusinessException.class)
	public String businessExceptionHandler(BusinessException e,final HttpServletRequest request){		
		FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
		outputFlashMap.put("errorMessage", e.getExceptionCause().toString());
		if(e.getExceptionCause().equals(ExceptionCause.NOT_AUTHORIZED)){
			outputFlashMap.put("errorNumber", "403");
		}else{
			outputFlashMap.put("errorNumber", "404");
		}
		return "redirect:/error";
	}
}
