package com.userapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.userapp.model.User;
import com.userapp.service.UserService;

@RestController
public class HystrixController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/newusers", produces="application/json")
	public List<User> findAll(){
		return userService.findAll();
	}
	@RequestMapping(value="/newusers/{userid}", produces="application/json")
	public User findById(@PathVariable("userid") int id){
		return userService.findById(id);
	}
	@RequestMapping(value="/hello/{username}")
	public String greet(@PathVariable("username")String name){
		return userService.greet(name);
	}
}
