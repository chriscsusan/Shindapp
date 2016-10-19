package co.grandcircus.shindapp.controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;

import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.shindapp.dao.ItemDao;
import co.grandcircus.shindapp.dao.SignupDao;
import co.grandcircus.shindapp.model.Allergen;
import co.grandcircus.shindapp.model.Item;
import co.grandcircus.shindapp.model.ItemSession;
import co.grandcircus.shindapp.model.Signup;
import co.grandcircus.shindapp.model.User;
//import co.grandcircus.shindapp.rest.ItemService;
import co.grandcircus.shindapp.rest.*;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private SignupDao signupDao;

	@Value("${api_key}")
	private String apiKey;

	@Value("${test_session}")
	private String testSession;

	@RequestMapping(value = "/item-info", method = RequestMethod.GET)
	public String itemInfo(Locale locale, Model model, Item item, RedirectAttributes redirectAttrs, @RequestParam(value = "id", required = true) int id) {

		Signup user = new Signup();
		user.setId(id);
		try {
			user.setPin(signupDao.getSignupPin(id));
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		model.addAttribute("ingredients", itemDao.getAllIngredients(user).getIngredients());
		model.addAttribute("user", user);
		model.addAttribute("item", item);
		model.addAttribute("start", 1);
		model.addAttribute("id", id);
		model.addAttribute("showAll", model.asMap().get("showAll"));
		System.out.println(model.asMap().get("showAll"));
		
		try {
			model.addAttribute("pin", signupDao.getSignupPin(id));
			model.addAttribute("signupEntry", signupDao.getSignup(id));
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("/item-info GET");
		return "item-info";
	}

	@RequestMapping(value = "/item-info", method = RequestMethod.POST)
	public String itemInfoPost(Locale locale, Model model, Item item, Signup signup,
			@RequestParam(value = "id", required = true) int id, RedirectAttributes redirectAttrs) {
		Signup user = new Signup();
		try {
			signupDao.updateSignup(signup);
			System.out.println(signup.getPin());
			System.out.println(signupDao.getSignupPin(id));
			redirectAttrs.addFlashAttribute("showAll", model.asMap().get("showAll"));
			model.addAttribute("showAll", model.asMap().get("showAll"));
			if (signupDao.getSignupPin(id) == signup.getPin()) {
				redirectAttrs.addFlashAttribute("showAll", true);
			}
		} catch (NumberFormatException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setId(id);
		model.addAttribute("ingredients", itemDao.getAllIngredients(user).getIngredients());
		model.addAttribute("user", user);
		model.addAttribute("id", id);
		System.out.println("/item-info POST");
		String returnStatement = "redirect:/item-info?id=";
		returnStatement += id;
		return returnStatement;
	}


	@RequestMapping(value = "/item-search", method = RequestMethod.GET)
	public String itemSearchGet(Item item, Signup user, Locale locale, Model model,
			@RequestParam(value = "q", required = true) String searchTerms,
			@RequestParam(value = "start", required = true) int start,
			@RequestParam(value = "id", required = true) int id, RedirectAttributes redirectAttrs) {
		try {
			model.addAttribute("showAll", model.asMap().get("showAll"));
			redirectAttrs.addFlashAttribute("showAll", true);
			item.setParticipantID(id);
			if (start == 1) {
				model.addAttribute("results",
						itemService.getItemInfoByNameTenResults(testSession, searchTerms, apiKey));

			}
			model.addAttribute("results",
					itemService.getItemInfoByNameNextTenResults(testSession, searchTerms, apiKey, start));
			model.addAttribute("searchTerms", searchTerms);
			model.addAttribute("id", id);
			model.addAttribute("start", start);
			model.addAttribute("ingredients", itemDao.getAllIngredients(user).getIngredients());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			model.addAttribute("signupEntry", signupDao.getSignup(id));
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("/item-search GET");
		return "item-search";
	}

	@RequestMapping(value = "/item-search", method = RequestMethod.POST)
	public String itemSearchPost(Item item, User user, Locale locale, Model model,
			@RequestParam(value = "q", required = true) String searchTerms,
			@RequestParam(value = "start", required = true) int start,
			@RequestParam(value = "id", required = true) int id, RedirectAttributes redirectAttrs) {
		model.addAttribute("showAll", model.asMap().get("showAll"));
		item.setParticipantID(id);
		model.addAttribute("searchTerms", searchTerms);
		model.addAttribute("id", id);
		model.addAttribute("start", start);
		redirectAttrs.addFlashAttribute("showAll", true);
		try {
			model.addAttribute("signup", signupDao.getSignup(id));
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			itemDao.addIngredient(user, item);
			itemDao.addAllergens(item, itemService.getItemInfoByUPC(item.getUpc()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("/item-search POST");
		String redirect = "redirect:/item-info?id=";
		redirect += id;

		return redirect;
	}



	@RequestMapping(value = "/item-info/{id}/delete", method = RequestMethod.POST)
	public String deleteIngredient(@PathVariable int id, Model model, User user, Item item) {
		try {
			user.setId(id);
			System.out.println("Food name:" + item.getFoodName());
			itemDao.deleteIngredient(item, user);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.asMap().clear();
		System.out.println("/item-info/id/delete GET");
		String redirect = "redirect:/item-info?id=";
		redirect += id;

		return redirect;
	}
//	@RequestMapping(value = "/item/{id}", method = RequestMethod.POST)
//	public String participantId(User user, Item item, Model model, @PathVariable int id, String searchTerms) {
//
//		try {
//
//			model.addAttribute("results", itemService.getItemInfoByName(testSession, searchTerms, apiKey));
//			item.setParticipantID(id);
//			itemDao.addIngredient(user, item);
//			model.addAttribute("id", id);
//
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("/item/id POST");
//		return "item";
//	}

//	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
//	public String getParticipantId(User user, Item item, Model model, @PathVariable int id, String searchTerms) {
//		// logger.info("Welcome home! The client locale is {}.", locale);
//		try {
//
//			model.addAttribute("results", itemService.getItemInfoByName(testSession, searchTerms, apiKey));
//			item.setParticipantID(id);
//			itemDao.addIngredient(user, item);
//			model.addAttribute("id", id);
//
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("/item/id GET");
//		return "item";
//	}
//	@RequestMapping(value = "/item-info/is_participant")
//	public String itemInfoForParticipant(Locale locale, Model model, Item item,
//			@RequestParam(value = "id", required = true) int id,
//			@RequestParam(value = "isParticipant", required = false) int isParticipant) {
//
//		Signup user = new Signup();
//		user.setId(id);
//		model.addAttribute("ingredients", itemDao.getAllIngredients(user).getIngredients());
//		model.addAttribute("user", user);
//		model.addAttribute("item", item);
//		model.addAttribute("start", 1);
//		model.addAttribute("id", id);
//		try {
//			
//		
//		} catch (NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("/item-info/is_participant GET");
//		String returnStatement = "redirect:/item-info?id=";
//		returnStatement += id;
//		return returnStatement;
//	}
	
//	@RequestMapping(value = "/item", method = RequestMethod.GET)
//	public String participantList(User user, Item item, Locale locale, Model model,
//			@RequestParam(value = "q", required = false) String searchTerms,
//			@RequestParam(value = "id", required = true) int id,
//			@RequestParam(value = "start", required = true) int start) {
//
//		try {
//			item.setParticipantID(id);
//			if (start == 1) {
//				model.addAttribute("results",
//						itemService.getItemInfoByNameTenResults(testSession, searchTerms, apiKey));
//
//			}
//			model.addAttribute("results",
//					itemService.getItemInfoByNameNextTenResults(testSession, searchTerms, apiKey, start));
//			model.addAttribute("searchTerms", searchTerms);
//			model.addAttribute("id", id);
//			model.addAttribute("start", start);
//
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("/item GET");
//		return "item";
//	}

//	@RequestMapping(value = "/item", method = RequestMethod.POST)
//	public String participantListPost(User user, Item item, Model model,
//			@RequestParam(value = "q", required = false) String searchTerms,
//			@RequestParam(value = "id", required = true) int id) {
//
//		try {
//
//			model.addAttribute("results", itemService.getItemInfoByNameTenResults(testSession, searchTerms, apiKey));
//			itemDao.addIngredient(user, item);
//
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("/item POST");
//		String redirect = "redirect:/item-info?id=";
//		redirect += id;
//
//		return redirect;
//	}
//	@RequestMapping(value = "/allergens/{id}", method = RequestMethod.GET)
//	public String itemAllergenInfo(Locale locale, Model model, @PathVariable String id) {
//		try {
//			Signup signup = new Signup();
//			signup.setId(Integer.parseInt(id));
//
//			model.addAttribute("allergens", itemDao.getAllergens(signup));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "allergens";
//	}


}
