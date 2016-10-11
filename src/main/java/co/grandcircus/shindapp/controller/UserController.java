package co.grandcircus.shindapp.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserController {
	
	@RequestMapping(value = "/participants", method = RequestMethod.GET)
	public String participantList(Locale locale, Model model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		

		
		return "home";
	}

}
