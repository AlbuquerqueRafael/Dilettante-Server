package com.api.dl.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class UserController {
	
  @Autowired
  private UserService userService;

  @Autowired 
  private UserValidator userValidator;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public Map<String, String> login (@RequestBody User user) {
    userValidator.validateLogin(user);                                  
    Map<String, String> response = userService.login(user.getUsername(), user.getPassword());

		return response;
  }

  @RequestMapping(value = "/protected", method = RequestMethod.GET)
	public Map<String, String> protectedTEst () {
    Map<String, String> response = new HashMap<String, String>();
    response.put("response", "worked dude");
		return response;
  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Map<String, String> signup (@RequestBody User user) {
    userValidator.validateSignUp(user);
		return userService.signup(user);
  }
  
}