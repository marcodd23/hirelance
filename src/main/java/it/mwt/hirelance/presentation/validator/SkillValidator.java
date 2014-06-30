package it.mwt.hirelance.presentation.validator;

import it.mwt.hirelance.business.model.Skill;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SkillValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Skill.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Skill skill = (Skill) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "errors.required");
	}

}
