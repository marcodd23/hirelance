package it.mwt.hirelance.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class ErrorController {

	@RequestMapping("/error")
	public String errorPageViews(Model model, HttpServletRequest request,
			HttpSession session) {
		if (RequestContextUtils.getInputFlashMap(request) == null) {
			model.addAttribute("errorMessage", "Page Not Found!");
			model.addAttribute("errorNumber", "404");
		}
		return "error-page.views";

	}
}
