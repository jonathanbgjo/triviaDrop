package com.trivia.web.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trivia.web.models.Trivia;
import com.trivia.web.models.User;
import com.trivia.web.services.HomeService;


@Controller
public class HomeController {
	private final HomeService homeService;

	private Integer lastScore= 0;

	// Constructor
	public HomeController(HomeService homeService) {
		this.homeService = homeService;
	}

	// Show LoginPage
	@RequestMapping("/")
	public String loginPage(@ModelAttribute("user") User user) {
		return "login";
	}
		
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String  password , HttpSession session,Model model,RedirectAttributes redirectAttributes) {
			
	if ( homeService.login(email , password, session) ) {
		return "redirect:/game";
		}
	else {
		System.out.println("in else");
		redirectAttributes.addFlashAttribute("error", "Email And Password do not match. Please try again !!");
		return "redirect:/";
			} 	
		}
	

	// Handle adding the user to the database
	@PostMapping("/registration")
	public String user_handler(HttpSession session, @RequestParam("passwordConfirmation") String passwordConfirmation, @Valid @ModelAttribute("user")User user, BindingResult result, Model model) {
		System.out.println(user.getEmail() + "hehehe");
		if( result.hasErrors()) {
			return "redirect:/";
		}else {
			homeService.saveUser(user , passwordConfirmation, session);
			if(session.getAttribute("userid") != null) {
				return "redirect:/";
			}else {
				return "redirect:/";
			}
		}
	}

	// Handle request for homepage
	@RequestMapping("/game")
	public String index(Model model) {
		model.addAttribute("categories", this.homeService.keysToList());
		model.addAttribute("users", homeService.findAllUser());
		return "index";
	}

	// Make an api request based off of users info from form
	@PostMapping("/processRequest")
	public String process(@RequestParam("category") String userCategory,
			@RequestParam("numberOfQuestions") Integer numberOfQuestions, @RequestParam("difficulty") String difficulty,
			HttpSession session) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		// My category, numberofquestions, and difficulty
		String numOfQ = numberOfQuestions.toString();

		// Find corresponding number that matches category
		HashMap<String, Integer> categories = this.homeService.retrieveCategories();
		String categoryNum = categories.get(userCategory).toString();

		String url = "https://opentdb.com/api.php?amount=".concat(numOfQ).concat("&category=").concat(categoryNum)
				.concat("&difficulty=").concat(difficulty).concat("&type=multiple");

		// Java Object to Json
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// json to java Object
		Trivia request = objectMapper.readValue(new URL(url), Trivia.class);
		ArrayList<Trivia> results = (ArrayList<Trivia>) request.getResults();

		// Iterate through results
		List<Trivia> questions = objectMapper.convertValue(results, new TypeReference<List<Trivia>>() {
		});
		session.setAttribute("results", questions);

		return "redirect:/display";
	}

	// Handle route to display dashboard
	@RequestMapping("/display")
	public String showDisplay(HttpSession session, Model model) {

		List<Trivia> questions = new ArrayList<Trivia>();
		List<String> answers = new ArrayList<String>();
		List<Trivia> results = (List) session.getAttribute("results");

		for (Trivia trivia : results) {
			questions.add(trivia);
		}

		// Loop through trivia objects and get incorrect and correct answers
		model.addAttribute("triviaObjs", questions);
		session.setAttribute("triviaObjs", questions);
		return "display";
	}

	// Handle to calculate score
	@RequestMapping("/process")
	public String calculateScore(HttpSession session, @RequestParam("answer") String answer,
			RedirectAttributes redirectAttributes) {
		lastScore = 0;
		List<Trivia> triviaObjs = (List) session.getAttribute("triviaObjs");
		String[] my_answers = answer.split(",");

		for (int i = 0; i < my_answers.length; i++) {
			if (triviaObjs.get(i).getCorrect_answer().equals(my_answers[i])) {
				this.lastScore++;
			}
		}
		String score = this.lastScore.toString();
		redirectAttributes.addFlashAttribute("message", "Your Last Score was:".concat(score));
		homeService.saveScore(this.lastScore, session); 
		
		
		return "redirect:/game";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
