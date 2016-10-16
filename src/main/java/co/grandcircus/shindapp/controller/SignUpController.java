package co.grandcircus.shindapp.controller;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.grandcircus.shindapp.dao.SignupDao;
import co.grandcircus.shindapp.model.Signup;

@Controller
public class SignUpController {

	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	private SignupDao signupDao;

	@RequestMapping(value = "/sign-up", method = RequestMethod.GET)
	public String listSignupView(Model model) {
		model.addAttribute("list", signupDao.getAllSignup());
		model.addAttribute("signup", new Signup());
		System.out.println("/signup -> sign-up.jsp");
		return "sign-up";
	}

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String listSignup(Model model, Signup signup) {

		model.addAttribute("list", signupDao.getAllSignup());
		signupDao.addSignup(signup);
		System.out.println("/signup -> sign-up.jsp");
		return "sign-up";
	}

	@RequestMapping(value = "/sign-up/{id}", method = RequestMethod.POST)
	public String saveMovie(@PathVariable int id, Signup signup, Model model) {
		try {
			signupDao.updateSignup(signup);
			model.addAttribute("list", signupDao.getAllSignup());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("id", id);

		logger.info("POST /sign-up/" + id + " -> sign-up.jsp");
		return "sign-up";
	}

	@RequestMapping(value = "/sign-up/{id}/delete", method = RequestMethod.POST)
	public String deleteMovie(@PathVariable int id, Model model) {
		try {
			signupDao.deleteSignup(id);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.asMap().clear();

		logger.info("POST /sign-up/" + id + "/delete -> redirect to /sign-up");
		return "redirect:/sign-up";
	}

	@RequestMapping(value = "/sign-up/{id}", method = RequestMethod.GET)
	public String getSignup(@PathVariable int id, Signup signup, Model model) {
		try {
			model.addAttribute("list", signupDao.getAllSignup());
			model.addAttribute("signup", signupDao.getSignup(id));
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("id", id);

		logger.info("GET /sign-up/" + id + " -> sign-up.jsp");
		return "sign-up";
	}

}
