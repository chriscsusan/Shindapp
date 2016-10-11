package co.grandcircus.shindapp.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class ItemController {

	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public String participantList(Locale locale, Model model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		
		
		return "item";
	}
	
}
