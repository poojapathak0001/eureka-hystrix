package com.userapp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.userapp.model.User;

@Service
public class UserService {
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="fallbackFindAll")
	public List<User> findAll(){
		String uri="http://EUREKA-CLIENT/users";
		List<User> userList = restTemplate.getForObject(uri,List.class);
		return userList;
	}
	public List<User> fallbackFindAll(){
		List<User> userList = 
		Arrays.asList(new User("no user","no email",10));
		return userList;
	}
	@HystrixCommand(fallbackMethod="fallbackFindOne")
	public User findById(@PathVariable("userid") int id){
		String uri="http://EUREKA-CLIENT/users/"+id;
		User user = restTemplate.getForObject(uri,User.class);
		return user;
	}
	public User fallbackFindOne(@PathVariable("userid") int id){
		User user = new User("no user","no email",10);
		return user;
	}
	@HystrixCommand(fallbackMethod="fallbackGreet")
	public String greet(@PathVariable("username")String name){
		String uri="http://EUREKA-CLIENT/say-hello/"+name;
		String msg = restTemplate.getForObject(uri,String.class);
		return msg;
	}
	public String fallbackGreet(@PathVariable("username")String name){
		String msg = "Come Later";
		return msg;
	}

}
