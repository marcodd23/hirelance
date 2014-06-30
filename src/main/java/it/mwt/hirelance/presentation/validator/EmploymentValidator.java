package it.mwt.hirelance.presentation.validator;

import java.util.Date;

import it.mwt.hirelance.business.model.Employment;
import it.mwt.hirelance.common.spring.validation.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmploymentValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		Employment.class.isAssignableFrom(klass);
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Employment employment = (Employment)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startJob", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endJob", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "description", "errors.maxlength", employment.getDescription(), 255);
		if(employment.getStartJob()!=null){
			if(employment.getStartJob().after(new Date())){
				errors.rejectValue("startJob", "errors.dateAfter");
			}
			if(employment.getEndJob()!=null){
				if(employment.getEndJob().before(employment.getStartJob())){
					errors.rejectValue("endJob", "errors.endJob");
				}
			}
		}
	}

}
