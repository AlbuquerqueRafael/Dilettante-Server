package com.api.dl.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.api.dl.security.JWTTokenProvider;
import com.api.dl.user.exceptions.InvalidCredentialsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private JWTTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private PasswordEncoder passwordEnconder;

  private Map<String, String> response = new HashMap<String, String>();

  public Map<String, String> login (String username, String password) {
    Optional<User> optUser = userRepository.findByUsername(username);

    if (!optUser.isPresent()) {
      throw new InvalidCredentialsException("Please, provide a valid username and password");
    }

    User user = optUser.get();
    
    if (!passwordEnconder.matches(password, user.getPassword())) {
      throw new InvalidCredentialsException("Please, provide a valid username and password");
    }

    String token = authUserAndGetToken(username, password, user.getRoles());

    response.put("response", "Login worked");
    response.put("token", token);

    return response;
  }

  public Map<String, String> signup (User user) {
    List<Role> roles = new ArrayList<Role>();
    roles.add(Role.ROLE_CLIENT);

    user.setPassword(passwordEnconder.encode(user.getPassword()));
    user.setRoles(roles);
    user.setActive(true);
    userRepository.save(user);
    response.put("response", "Sign up worked");

    return response;
  }

  private String authUserAndGetToken (String username, String password, List<Role> roles) {
    try{
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username, roles);
    } catch (AuthenticationException e) {
      throw new InvalidCredentialsException("Invalid token");
    }
  }

  public User getLoggedUser() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    return userRepository.findByUsername(username).get();
  }

  
}