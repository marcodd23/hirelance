package it.mwt.hirelance.business;


import it.mwt.hirelance.business.model.UploadedFile;

public interface ImageService {

	void saveProfileImage(UploadedFile image, int userID, String relativePath) throws BusinessException;
	void updateProfileImage(UploadedFile image, UploadedFile oldImage) throws BusinessException;
	void deleteImages(String folderPath) throws BusinessException;
	UploadedFile getImage(int imageID) throws BusinessException;	
}
