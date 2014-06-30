package it.mwt.hirelance.business.impl.disk;

import java.io.File;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.ImageService;
import it.mwt.hirelance.business.model.UploadedFile;
import it.mwt.hirelance.common.util.UploadUtility;

@Service
public class DISKImageService implements ImageService {

	//@Value("${filesystem.path}")
	//@Value("#{uploadBasePath.filesystem.path}")
	@Value("#{uploadPaths.basePath}")
	private String basePath;
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public void saveProfileImage(UploadedFile image, int userID, String relativePath) throws BusinessException {
		System.out.println("======================= basePAth= " + basePath);
		
		System.out.println("relativePath: " + relativePath);
		
		image.setRelativePath(userID + relativePath);
		
		File file = new File(basePath + image.getRelativePath() + image.getFileName());
		
		try {
			FileUtils.writeByteArrayToFile(file, image.getArrayByte());
			System.out.println("  ======================= FILE UPLOAD OK ============================== ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public UploadedFile getImage(int imageID) throws BusinessException {
       
        UploadedFile image = em.find(UploadedFile.class, imageID);
        
        File file = new File(basePath + image.getRelativePath() + image.getFileName());
        
        try {
			image.setArrayByte(FileUtils.readFileToByteArray(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
		return image;
	}

	@Override
	public void updateProfileImage(UploadedFile image, UploadedFile oldImage) throws BusinessException {
		
		System.out.println("OldPath: " + oldImage.getRelativePath());
		File oldFile = new File(basePath + oldImage.getRelativePath() + oldImage.getFileName());
		File newFile = new File(basePath + oldImage.getRelativePath() + image.getFileName());
		image.setRelativePath(oldImage.getRelativePath());
		oldFile.delete();
		
        	try {
        		FileUtils.writeByteArrayToFile(newFile, image.getArrayByte());
				System.out.println("  ======================= NUOVO FILE UPLOAD OK ============================== ");
				System.out.println(basePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     }
	
	@Override
	public void deleteImages(String folderPath) throws BusinessException{
		//System.out.println("La directory da cancellare è: "+basePath+folderPath);
		File folder = new File(basePath+folderPath);
		if(folder.exists()){
			try {
				UploadUtility.deleteProfileFiles(folder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		else{
			//System.out.println("La directory non esiste");
		}
	}
	
	

}
