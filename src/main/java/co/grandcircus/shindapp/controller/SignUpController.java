package co.grandcircus.shindapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.grandcircus.movies.dao.MovieDao;
import labA.Movie;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.grandcircus.movies.controller.MovieController;
import co.grandcircus.shindapp.dao.SignupDao;
@Controller
@RequestMapping(value = "/signup")
public class SignUpController {
	
	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);
	
	@Autowired
	private SignupDao signupDao;
	
	
	
	
	@RequestMapping(value = "/sign-up", method = RequestMethod.GET)
	public String listSignup(Model model) {
		model.addAttribute("Signup", signupDao.getAllSignup());

		System.out.println("/signup -> sign-up.jsp");
		
		return "sign-up";
	}

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String listSignup(Model model) {
		model.addAttribute("Signup", signupDao.getAllSignup());

		System.out.println("/signup -> sign-up.jsp");
		
		return "sign-up";
	}

}
