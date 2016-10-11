package co.grandcircus.shindapp.controller;

import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import co.grandcircus.shindapp.rest.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;


	public String participantList(Locale locale, Model model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		model.addAttribute("results", itemService.getItemInfoByName(itemService.getSessionID(ItemService.getKey()), "hummus", ItemService.getKey()));
		
		
		
		return "item";
	}
	
}
