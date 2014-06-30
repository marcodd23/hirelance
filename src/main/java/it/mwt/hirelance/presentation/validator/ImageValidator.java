package it.mwt.hirelance.presentation.validator;

import java.util.Collection;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class ImageValidator{

	/*@Autowired
	MessageSource messageSource;*/
	
/*	@Value("${error.image.contenttype.notmatch}")
	private String error1;
	@Value("${error.image.size}")
	private String error2;*/
	
	public void validate(MultipartFile image , Collection<String> errorMsg) {
	
		
		if(!(image.getContentType().equals("image/jpeg") | image.getContentType().equals("image/png")) ) {
		errorMsg.add("error.image.contenttype.notmatch");
			}
		
		if(image.getSize() > 5000000L) {
			errorMsg.add("error.image.size");
			}
		
	}

}
