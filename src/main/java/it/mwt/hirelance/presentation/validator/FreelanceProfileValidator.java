package it.mwt.hirelance.presentation.validator;

import it.mwt.hirelance.business.model.FreelanceProfile;
import it.mwt.hirelance.common.spring.validation.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FreelanceProfileValidator implements Validator{

	@Override
	public boolean supports(Class<?> klass) {
		return FreelanceProfile.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FreelanceProfile freelancerProfile = (FreelanceProfile)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "freelanceName", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "freelanceName", "errors.maxlength", freelancerProfile.getFreelanceName(), 20);
	}
	
	

}
