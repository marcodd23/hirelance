package it.mwt.hirelance.business.impl.jpa;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.CurriculumService;
import it.mwt.hirelance.business.model.Curriculum;
import it.mwt.hirelance.business.model.Education;
import it.mwt.hirelance.business.model.Employment;
import it.mwt.hirelance.business.model.Language;
import it.mwt.hirelance.business.model.Skill;

@Service
public class JPACurriculumService implements CurriculumService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void createCurriculum(Curriculum curriculum) throws BusinessException{
		em.persist(curriculum);
	}
	
	@Override
	public Curriculum findCurriculumById(int curriculumID) throws BusinessException{
		return em.find(Curriculum.class, curriculumID);
	}
	
	@Transactional
	@Override
	public void updateCurriculum(Curriculum curriculum) throws BusinessException {
		em.merge(curriculum);
	}
	
	@Override
	@Transactional
	public void createEducation(Education education) throws BusinessException {
		em.persist(education);
	}
	
	@Override
	@Transactional
	public void createEmployment(Employment employment) throws BusinessException {
		em.persist(employment);
	}
	
	@Override
	@Transactional
	public void createLanguage(Language language) throws BusinessException {
		em.persist(language);
	}
	
	@Override
	@Transactional
	public void removeSkill(Curriculum c, int skillID) throws BusinessException {
			for (Iterator<Skill> i=c.getCvSkills().iterator();i.hasNext();) {
				if(i.next().getSkillID()==skillID)i.remove();
			}
			this.updateCurriculum(c);
	}
	
	@Override
	@Transactional
	public void addSkill(Curriculum c, Skill s) throws BusinessException {
		c.addCvSkill(s);
		this.updateCurriculum(c);
	}

	@Override
	public Education findEducationById(int educationID)
			throws BusinessException {
		return em.find(Education.class, educationID);
	}
	
	@Transactional
	@Override
	public void updateEducation(Education education) throws BusinessException {
		em.merge(education);
	}
	
	@Transactional
	@Override
	public void deleteEducation(Education education)
			throws BusinessException {
		em.remove(em.merge(education));
		em.getEntityManagerFactory().getCache().evictAll();
	}
	
	@Override
	public Employment findEmploymentById(int employmentID)
			throws BusinessException {
		return em.find(Employment.class, employmentID);
	}
	
	@Transactional
	@Override
	public void updateEmployments(Employment employment)
			throws BusinessException {
		em.merge(employment);		
	}
	
	@Transactional
	@Override
	public void deleteEmployment(Employment employment)
			throws BusinessException {
		em.remove(em.merge(employment));
		em.getEntityManagerFactory().getCache().evictAll();
	}
	
	@Override
	public Language findLanguageById(int langID) throws BusinessException {
		return em.find(Language.class, langID);
	}
	
	@Transactional
	@Override
	public void updateLanguage(Language language) throws BusinessException {
		em.merge(language);		
	}
	
	@Transactional
	@Override
	public void deleteLanguage(Language language) throws BusinessException {
		em.remove(em.merge(language));
		em.getEntityManagerFactory().getCache().evictAll();
	}
}
