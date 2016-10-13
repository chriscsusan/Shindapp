package co.grandcircus.shindapp.controller;


import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.shindapp.model.ItemSession;
//import co.grandcircus.shindapp.rest.ItemService;
import co.grandcircus.shindapp.rest.*;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public String participantList(Locale locale, Model model, @RequestParam(value="q", required=true) String searchTerms) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		String session = itemService.getSession().getSession();
		
		
		
		try {
			model.addAttribute("results", itemService.getItemInfoByName(session, searchTerms, itemService.getKey()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		

		
		return "item";
	}

	
	@RequestMapping(value = "/item-info/{result}", method = RequestMethod.GET)
	public String itemInfo(Locale locale, Model model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		String session = itemService.getSession().getSession();
		
		try {
			model.addAttribute("results", itemService.getItemInfoByName(session, "hummus", itemService.getKey()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return "item-info";
	}
	
	@RequestMapping(value = "/item/{upc}", method = RequestMethod.GET)
	public String itemAllergenInfo(Locale locale, Model model, @PathVariable String upc) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		model.addAttribute("allergens", itemService.getItemInfoByUPC(upc));
		return "allergens";
	}
	
	@RequestMapping(value = "/item-search", method = RequestMethod.GET)
	public String itemSearch(Locale locale, Model model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		return "item-search";
	}

}
