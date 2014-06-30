package it.mwt.hirelance.presentation;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.ImageService;
import it.mwt.hirelance.business.model.UploadedFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/files")
public class FilesController {

	@Autowired
    private ImageService imageService;
	
/*	@RequestMapping(value="/client/{imageID}", method=RequestMethod.GET)
	private HttpEntity<byte[]> getImage(@PathVariable int imageID) throws BusinessException{
		
		Image image = imageService.getImage(imageID);
		
		byte[] arrayByte = image.getArrayByte();
		
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(new MediaType(image.getContentType()));
		headers.setContentLength(arrayByte.length);
		
		return new HttpEntity<byte[]>(arrayByte, headers);
	}*/
	
	@RequestMapping(value="/image/client/{imageID}", method=RequestMethod.GET)
	public void getImage(@PathVariable("imageID") int imageID, HttpServletResponse response) throws BusinessException{
		
		UploadedFile image = imageService.getImage(imageID);
		
		byte[] arrayByte = image.getArrayByte();

		response.setContentType(image.getContentType());
		
		try {
			response.getOutputStream().write(arrayByte);
			response.getOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/image/freelancer/{imageID}", method=RequestMethod.GET)
	public void getFreelanceImage(@PathVariable("imageID") int imageID, HttpServletResponse response) throws BusinessException{
		
		UploadedFile image = imageService.getImage(imageID);
		
		byte[] arrayByte = image.getArrayByte();

		response.setContentType(image.getContentType());
		
		try {
			response.getOutputStream().write(arrayByte);
			response.getOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
