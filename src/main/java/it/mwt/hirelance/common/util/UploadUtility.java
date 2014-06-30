package it.mwt.hirelance.common.util;

import it.mwt.hirelance.business.model.UploadedFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


public class UploadUtility {


	public static UploadedFile setupFileToUpload(MultipartFile file){
		
		UploadedFile image = new UploadedFile();
		
		try {
			image.setArrayByte(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String fileExtension = "";
		String originalFileName = file.getOriginalFilename();
		int i = originalFileName.lastIndexOf(".");
		if(i>0){
			fileExtension = originalFileName.substring(i+1);
		}
		
		String timeStamp = new Timestamp(new Date().getTime()).toString();
		image.setFileName(timeStamp + "." + fileExtension);
		image.setContentType(file.getContentType());
		
		return image;
	}
	
	public static void deleteProfileFiles(File file) throws IOException{
	 
	    	if(file.isDirectory()){
	    		//System.out.println("Il file è una folder ");
	    		if(file.list().length==0){
	    			//System.out.println("vuota. La cancello");
	    		   file.delete();
	    		}
	    		else{
		    		//System.out.println("Il file è una folder piena:");
	        	   String files[] = file.list();
	        	   for (String temp : files) {
	   	    		//System.out.println("Cancello "+temp);
	        	      //construct the file structure
	        	      File fileDelete = new File(file, temp);
	        	      //recursive delete
	        	      deleteProfileFiles(fileDelete);
	        	   }
	        	   //check the directory again, if empty then delete it
	        	   if(file.list().length==0){
	           	     file.delete();
	        	   }
	    		}
	 
	    	}else{
	    		//System.out.println("Il file è un file");
	    		//if file, then delete it
	    		file.delete();
	    	}
	    }
	
}
