package com.api.dl.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class UserController {
	
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public Map<String, String> login (@RequestParam("username") String username, 
                                    @RequestParam("password") String password) {
    
	
    Map<String, String> response = userService.login(username, password);

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
		return userService.signup(user);
  }
  
}