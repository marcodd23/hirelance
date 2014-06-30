package it.mwt.hirelance.presentation.validator;

import java.util.regex.Pattern;

import it.mwt.hirelance.business.model.User;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
	
	private final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern;
	
	@Override
	public boolean supports(Class<?> klass) {
		return User.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {

	}
	
	public void validateForm(Object target, Errors errors, String confirmEmail, String confirmPassword, boolean existUsername, boolean existEmail){
		User user= (User) target;
		pattern = Pattern.compile(emailPattern);
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.required");
		if(user.getPassword().length()<8){
			errors.rejectValue("password", "errrors.min_password");
		}
        if(!confirmPassword.equals(user.getPassword())){
        	errors.rejectValue("password", "errors.notmatch.password");
        }
        if(!confirmEmail.equals(user.getEmail())){
        	errors.rejectValue("email", "errors.notmatch.email");
        }
		
        if(existUsername){
        	errors.rejectValue("username", "errors.username.already.exist");
        }
        
        if(!pattern.matcher(user.getEmail()).matches()){
        	errors.rejectValue("email", "errors.email");
        }
        
        if(existEmail){
        	errors.rejectValue("email", "errors.email.already.exist");
        }
      
	}

}
