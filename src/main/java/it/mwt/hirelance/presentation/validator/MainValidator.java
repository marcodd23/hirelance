package it.mwt.hirelance.presentation.validator;

import it.mwt.hirelance.business.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MainValidator implements Validator {

	@Override
	public boolean supports(Class<?> Klass) {
		
		return Category.class.isAssignableFrom(Klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Category mainCategory= (Category) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		
	}

	
}
