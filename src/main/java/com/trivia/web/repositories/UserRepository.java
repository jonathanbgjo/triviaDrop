package com.trivia.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.trivia.web.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
	
	List<User> findByOrderByScoreDesc();
}
