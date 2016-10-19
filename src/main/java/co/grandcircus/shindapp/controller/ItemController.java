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

	/*
	 * Get method loads the item-info page for a specific user.  It takes a url parameter to allow the specific participant
	 * to be chosen by the id.   
	 */
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

	/*
	 * Post method for item-info page that performs multiple functions.  There is an option to unlock edit functionality, and the code to unlock that
	 * is in this method as well.  It compares the user's PIN from the database to the PIN that is entered in the form.
	 * If it is correct, it reloads the page with a showAll attribute set to "true."  This value is passed to the search
	 * results page, so that if it is the next page in the user's navigation, it will still have the showAll attribute
	 * and the page will function correctly, allowing the user to add an ingredient from the search results to the table.
	 */
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

	/*
	 * Get method for the item-search page which queries the API.  It takes the q parameter in the url which is the 
	 * search terms.  The start parameter is the first result in the list that should be displayed.  The id parameter
	 * relates the the specific user and is used int he Post method.
	 */
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
	/*
	 * Post method for item-search page which allows the user to add an ingredient to the dish.
	 * It adds the ingredient to the table by calling upon the Dao and adding the item to the ingredient table.  It also
	 * uses a method to add allergen information for the ingredient to another table.  
	 */
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

	/*
	 * Post method for the delete page.  It uses the ID of the user and calls upon the Dao to delete the
	 * specified ingredient from db table.
	 */
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
	/*
	 * Get method for allergens page which displays the array of allergens present for the given dish.
	 * Dish is chosen by ID.  Page is used for testing purposes only.
	 */
	@RequestMapping(value = "/allergens/{id}", method = RequestMethod.GET)
	public String itemAllergenInfo(Locale locale, Model model, @PathVariable String id) {
		try {
			Signup signup = new Signup();
			signup.setId(Integer.parseInt(id));

			model.addAttribute("allergens", itemDao.getAllergens(signup));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "allergens";
	}


}
