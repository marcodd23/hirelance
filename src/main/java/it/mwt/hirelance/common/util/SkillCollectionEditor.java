package it.mwt.hirelance.common.util;

import it.mwt.hirelance.business.model.Skill;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

public class SkillCollectionEditor extends CustomCollectionEditor{

	public SkillCollectionEditor(Class<?> collectionType) {
		super(collectionType);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object convertElement(Object element) {
		
        if(element instanceof Skill){
        	return element;
        }
        
        if(element instanceof String){
        	int id = Integer.parseInt((String)element);
        	Skill result = new Skill();
        	result.setSkillID(id);
        	return result;
        }
		
        return null;
		//return super.convertElement(element);
	}



}
