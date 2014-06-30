package it.mwt.hirelance.presentation.validator;

import it.mwt.hirelance.business.model.Language;
import it.mwt.hirelance.common.spring.validation.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class LanguageValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		Language.class.isAssignableFrom(klass);
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Language language = (Language)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "name", "errors.maxlength", language.getName(), 30);
	}

}
