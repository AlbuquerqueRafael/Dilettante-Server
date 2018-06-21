package com.api.dl.reply;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.api.dl.thread.exceptions.InvalidThreadException;

import org.springframework.stereotype.Service;

@Service
public class ReplyValidator {
  private ValidatorFactory factory;
  private Validator validator;
  
  @PostConstruct
  void started() {
    this.factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }
  
  public void validateSave(Reply reply) {
    Set<ConstraintViolation<Reply>> threadViolations = validator.validate(reply);
    String errors = "";

    errors += getErrors(threadViolations, errors);

    if (!errors.equals("")) {
      throw new InvalidThreadException(errors);
    }
  }

  private String getErrors(Set<ConstraintViolation<Reply>> violations, String errors) {
    for (ConstraintViolation<Reply> violation : (Set<ConstraintViolation<Reply>>) violations) {
      errors += violation.getMessage() + ";";
    }

    return errors;
  }
  
}