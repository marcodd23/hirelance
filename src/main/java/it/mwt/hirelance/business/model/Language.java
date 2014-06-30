package it.mwt.hirelance.business.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Language
 *
 */
@Entity
@Table(name = "LANGUAGES")
public class Language implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6671063994191312059L;
	private int langID;
	private String name;
	private String reading;
	private String speaking;
	private String listening;
	private String writing;
	private String certification;

	public Language() {
		super();
	}

	public Language(String name, String reading, String speaking,
			String listening, String writing, String certification) {
		super();
		this.name = name;
		this.reading = reading;
		this.speaking = speaking;
		this.listening = listening;
		this.writing = writing;
		this.certification = certification;
	}

	public Language(int langID, String name, String reading, String speaking,
			String listening, String writing, String certification) {
		super();
		this.langID = langID;
		this.name = name;
		this.reading = reading;
		this.speaking = speaking;
		this.listening = listening;
		this.writing = writing;
		this.certification = certification;
	}
	
	@Id    
	@GeneratedValue(generator = "Language_Gen")
	@SequenceGenerator(name = "Language_Gen", sequenceName = "Language_Seq", initialValue=1, allocationSize=1)
	@Column(name = "LANGUAGE_ID")
	public int getLangID() {
		return this.langID;
	}

	public void setLangID(int langID) {
		this.langID = langID;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getReading() {
		return this.reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}   
	public String getSpeaking() {
		return this.speaking;
	}

	public void setSpeaking(String speaking) {
		this.speaking = speaking;
	}   
	public String getListening() {
		return this.listening;
	}

	public void setListening(String listening) {
		this.listening = listening;
	}   
	public String getWriting() {
		return this.writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}   
	public String getCertification() {
		return this.certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}
   
}
