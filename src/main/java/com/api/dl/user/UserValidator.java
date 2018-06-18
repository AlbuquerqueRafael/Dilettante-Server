package com.api.dl.user;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.api.dl.user.exceptions.InvalidCredentialsException;
import com.api.dl.user.exceptions.UserInvalidException;

import org.springframework.stereotype.Service;

@Service
public class UserValidator {
  private ValidatorFactory factory;
  private Validator validator;
  
  @PostConstruct
  void started() {
    this.factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }
  
  public void validateSignUp(User user) {
    Set<ConstraintViolation<User>> violations = validator.validate(user);
    String errors = "";

    for (ConstraintViolation<User> violation : violations) {
      errors += violation.getMessage() + ";";
    }

    if (!errors.equals("")){
      throw new UserInvalidException(errors);
    }
  }

  public void validateLogin(User user) {

    if (user == null || user.getPassword() == null || user.getPassword().trim().equals("") ||
        user.getUsername() == null || user.getUsername().trim().equals("")){
      throw new InvalidCredentialsException("Please, provide a valid username and password");
    }
  }


  
}