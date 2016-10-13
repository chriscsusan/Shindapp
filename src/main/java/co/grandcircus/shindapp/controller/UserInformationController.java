package co.grandcircus.shindapp.controller;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the ShindApp home page.
 */
@Controller
public class UserInformationController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "userinformation", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "userinformation";
	}
	
}
