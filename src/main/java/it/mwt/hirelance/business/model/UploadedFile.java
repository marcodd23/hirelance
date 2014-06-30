package it.mwt.hirelance.business.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "UPLOADED_FILE")
public class UploadedFile implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 683164577146713515L;
	private int fileID;
	private String relativePath;
	private String fileName;
	//private String imagePath;
	private String contentType;
	private byte[] arrayByte;
	
	
	
	public UploadedFile() {
		super();
	}
	
	@Id
	@Column(name = "FILE_ID")
	@GeneratedValue(generator = "File_Gen")
	@SequenceGenerator(name = "File_Gen", sequenceName = "File_Seq",initialValue=1, allocationSize=1)
	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Transient
	public byte[] getArrayByte() {
		return arrayByte;
	}
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setArrayByte(byte[] arrayByte) {
		this.arrayByte = arrayByte;
	}
	
}
