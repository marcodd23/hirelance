package it.mwt.hirelance.presentation.validator;

import it.mwt.hirelance.business.model.Category;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SubValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Category.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Category subCategory = (Category)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
	}

}
