package com.trivia.web.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.trivia.web.models.User;
import com.trivia.web.repositories.UserRepository;

@Service
public class HomeService {
	private HashMap<String, Integer> categories  = new HashMap<String, Integer>();
	private final UserRepository userRepository;
	public HomeService(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.categories.put("General Knowledge", 9);
		this.categories.put("Entertainment: Books", 10);
		this.categories.put("Entertainment: Film", 11);
		categories.put("Entertainment: Music",12);
		categories.put("Entertainment: Musical & Theatres",13);
		categories.put("Entertainment: Television",14);
		categories.put("Entertainment: Video Games",15);
		categories.put("Entertainment: Board Games",16);
		categories.put("Science & Nature",17);
		categories.put("Science: Computers",18);
		categories.put("Science: Mathematics",19);
		categories.put("Mythology",20);
		categories.put("Spots",21);
		categories.put("Geology",22);
		categories.put("History",23);
		categories.put("Politics",24);
		categories.put("Art",25);
		categories.put("Celebrities",26);
		categories.put("Animal",27);
		categories.put("Vehicles",28);
		categories.put("Entertainment: Comics",29);
		categories.put("Science, Gadets",30);
		categories.put("Entertainment: Japanese Anime & Manga",31);
		categories.put("Entertainment: Cartoon & Animations",32);
	}
	
	
	//Retrieve all categories 
	public HashMap retrieveCategories() {
		return this.categories;
	}
	
	//Create an Array of keys from hashmap
	public List keysToList() {
		List<String> listOfCategories = new ArrayList<String>();
		for(String key: this.categories.keySet()) {
			listOfCategories.add(key);
		}
		return listOfCategories;
	}

	//Registration
	public String saveUser(User user, String passwordConfirmation, HttpSession session) {
		
		if(!user.getPassword().equals(passwordConfirmation) ) {
			System.out.println("password dont match");
			return "password dont match";
		}
		System.out.println(user.getEmail()+"yoyoyo");
		System.out.println(null == userRepository.findByEmail(user.getEmail())); 
		User UserEmail = userRepository.findByEmail(user.getEmail());
		if( UserEmail != null ){
			System.out.println("Email Exist");
			return "Email Exist";			
		}
		else {
			System.out.println("UserEmail : " + UserEmail);
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
			userRepository.save(user);
			System.out.println("User id : " +  user.getId());	
			session.setAttribute("userid", user.getId());
			return "User Created";			
		}			
	}
	
	//Login 
	public boolean login(String email, String password, HttpSession session) {
		User user = userRepository.findByEmail(email);
		
		if( user != null) {
			if( BCrypt.checkpw(password, user.getPassword()) ) {
				session.setAttribute("userid", user.getId());	
				return true;
			}
			else {
				System.out.println("Password not correct");
				return false;
			}
		}
		System.out.println("Email & password not correct");
		return false;
	}


	public void saveScore(Integer score, HttpSession session) {
		// TODO Auto-generated method stub
		User user = userRepository.findOne((Long) session.getAttribute("userid"));
		int newScore = user.getScore() + score;
		user.setScore(newScore);
		userRepository.save(user);
	}
	
	public List<User> findAllUser(){
		return userRepository.findByOrderByScoreDesc();
	}


}
