package co.grandcircus.shindapp.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class SignUpController {
	
	@RequestMapping(value = "/sign-up", method = RequestMethod.GET)
	public String participantList(Locale locale, Model model) {
		//logger.info("Welcome Home! The client locale is {}.", locale);
		
		
		
		return "sign-up";
	}

}
