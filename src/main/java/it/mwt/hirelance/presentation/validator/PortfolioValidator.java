package it.mwt.hirelance.presentation.validator;

import it.mwt.hirelance.business.model.PortfolioItem;
import it.mwt.hirelance.common.spring.validation.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PortfolioValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {

		return PortfolioItem.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PortfolioItem portfolioItem = (PortfolioItem)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "errors.required");
		ValidationUtility.rejectIfMaxLength(errors, "description", "errors.maxlength", portfolioItem.getDescription(), 60);
	}

}
