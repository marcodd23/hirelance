package it.mwt.hirelance.presentation.validator;

import it.mwt.hirelance.business.model.ClientProfile;
import it.mwt.hirelance.common.spring.validation.ValidationUtility;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ClientProfileValidator implements Validator{

	@Override
	public boolean supports(Class<?> klass) {
		return ClientProfile.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ClientProfile clientProfile = (ClientProfile)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clientName", "errors.required");		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "errors.required");		
		ValidationUtility.rejectIfMaxLength(errors, "clientName", "errors.maxlength", clientProfile.getClientName(), 20);
		ValidationUtility.rejectIfMaxLength(errors, "companyName", "errors.maxlength", clientProfile.getCompanyName(), 20);
		ValidationUtility.rejectIfMaxLength(errors, "description", "errors.maxlength", clientProfile.getDescription(), 60);
	}
	

}
