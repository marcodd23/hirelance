package it.mwt.hirelance.business;

import it.mwt.hirelance.business.model.Curriculum;
import it.mwt.hirelance.business.model.Education;
import it.mwt.hirelance.business.model.Employment;
import it.mwt.hirelance.business.model.Language;
import it.mwt.hirelance.business.model.Skill;


public interface CurriculumService {
	
	void createCurriculum(Curriculum curriculum) throws BusinessException;

	Curriculum findCurriculumById(int curriculumID) throws BusinessException;

	void updateCurriculum(Curriculum curriculum) throws BusinessException;

	void createEducation(Education education) throws BusinessException;

	void createEmployment(Employment employment) throws BusinessException;

	void createLanguage(Language language) throws BusinessException;

	void removeSkill(Curriculum c, int skillID) throws BusinessException;
	
	void addSkill(Curriculum c, Skill s) throws BusinessException;

	Education findEducationById(int educationID) throws BusinessException;

	void updateEducation(Education education) throws BusinessException;

	void deleteEducation(Education education) throws BusinessException;

	Employment findEmploymentById(int employmentID) throws BusinessException;

	void updateEmployments(Employment employment) throws BusinessException;

	void deleteEmployment(Employment employment) throws BusinessException;

	Language findLanguageById(int langID) throws BusinessException;

	void updateLanguage(Language language) throws BusinessException;

	void deleteLanguage(Language language) throws BusinessException;
	
}
