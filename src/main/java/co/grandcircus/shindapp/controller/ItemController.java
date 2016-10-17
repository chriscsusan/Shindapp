package co.grandcircus.shindapp.controller;


import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import co.grandcircus.shindapp.dao.ItemDao;
import co.grandcircus.shindapp.model.Item;
import co.grandcircus.shindapp.model.ItemSession;
import co.grandcircus.shindapp.model.User;
//import co.grandcircus.shindapp.rest.ItemService;
import co.grandcircus.shindapp.rest.*;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDao itemDao;
	
	@Value("${api_key}")
	private String apiKey;
	
	@Value("${test_session}")
	private String testSession;
	
	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public String participantList(User user, Item item, Locale locale, Model model, @RequestParam(value="q", required=false) String searchTerms, @RequestParam(value="id", required=true) int id) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		try {
			item.setParticipantID(id);
			if(searchTerms==null){
				
			}
			model.addAttribute("results", itemService.getItemInfoByName(testSession, searchTerms, itemService.getKey()));
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		

		return "item";
	}

	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public String participantListPost(User user, Item item, Model model, @RequestParam(value="q", required=false) String searchTerms, @RequestParam(value="id", required=true) int id) {
		
		try {

			model.addAttribute("results", itemService.getItemInfoByName(testSession, searchTerms, apiKey));
			itemDao.addIngredient(user, item);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		String redirect = "redirect:/item-info?id=";
		redirect += id;
		
		return redirect;
	}
	
	@RequestMapping(value = "/item-info", method = RequestMethod.GET)
	public String itemInfo(Locale locale, Model model, Item item, @RequestParam(value="id", required=false) int id) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		User user = new User();
		user.setId(id);
		model.addAttribute("ingredients", itemDao.getAllIngredients(user).getIngredients());		
		model.addAttribute("user", user);
		model.addAttribute("item", item);
		return "item-info";
	}
	@RequestMapping(value = "/item-info", method = RequestMethod.POST)
	public String itemInfoPost(Locale locale, Model model, Item item, @RequestParam(value="id", required=false) int id) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		User user = new User();
		user.setId(id);
		try {
			itemDao.deleteIngredient(item, user);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("ingredients", itemDao.getAllIngredients(user).getIngredients());		
		model.addAttribute("user", user);
		
		return "item-info";
	}
	
	@RequestMapping(value = "/allergens/{upc}", method = RequestMethod.GET)
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

	@RequestMapping(value = "/item/{id}", method = RequestMethod.POST)
	public String participantId(User user, Item item, Model model, @PathVariable int id, String searchTerms) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		String session = itemService.getSession();
		try {
			
			model.addAttribute("results", itemService.getItemInfoByName(session, searchTerms, itemService.getKey()));
			item.setParticipantID(id);
			itemDao.addIngredient(user, item);
			model.addAttribute("id", id) ;
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "item";
	}
	
	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
	public String getParticipantId(User user, Item item, Model model, @PathVariable int id, String searchTerms) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		String session = itemService.getSession();
		try {
			
			model.addAttribute("results", itemService.getItemInfoByName(session, searchTerms, itemService.getKey()));
			item.setParticipantID(id);
			itemDao.addIngredient(user, item);
			model.addAttribute("id", id) ;
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return "item";
	}
	
}

