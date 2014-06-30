package it.mwt.hirelance.presentation.validator;

import java.util.Date;

import it.mwt.hirelance.business.model.Education;
import it.mwt.hirelance.common.spring.validation.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class EducationValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		Education.class.isAssignableFrom(klass);
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Education education = (Education) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "institute", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "grade", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "graduationDate", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "institute", "errors.maxLength", education.getInstitute(), 40);
		if(education.getGraduationDate()!=null){
			if(education.getGraduationDate().after(new Date())){
				errors.rejectValue("graduationDate", "errors.dateAfter");
			}
		}
	}

}
