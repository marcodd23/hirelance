package it.mwt.hirelance.presentation.validator;

import java.util.Map;

import it.mwt.hirelance.business.model.Project;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProjectValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		// TODO Auto-generated method stub
		return Project.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {

	}
	
	public void validateProject(Object target, Errors errors, int projectMainCategory, Map<String, String> errorMsg) {
		Project project = (Project)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "budgetMIN", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "budgetMAX", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "skills", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "errors.required");

		

        
        if(projectMainCategory==0){
        	errorMsg.put("mainCategorySelectError", "errors.required");
        }else{
            if(project.getProjectSubCategory().getCatID()==0){
            	errors.rejectValue("projectSubCategory.subID", "errors.required");
            }
        }   
        if(project.getBudgetMIN().compareTo(project.getBudgetMAX())==1){
        	errors.rejectValue("budgetMIN", "errors.budgetMinMore");
        }
	}

}
