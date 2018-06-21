package com.api.dl.thread;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.api.dl.thread.exceptions.InvalidThreadException;

import org.springframework.stereotype.Service;

@Service
public class ThreadValidator {
  private ValidatorFactory factory;
  private Validator validator;
  
  @PostConstruct
  void started() {
    this.factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }
  
  public void validateSave(Thread thread) {
    Set<ConstraintViolation<Thread>> threadViolations = validator.validate(thread);
    String errors = "";

    errors += getErrors(threadViolations, errors);

    if (!errors.equals("")) {
      throw new InvalidThreadException(errors);
    }
  }

  private String getErrors(Set<ConstraintViolation<Thread>> violations, String errors) {
    for (ConstraintViolation<Thread> violation : (Set<ConstraintViolation<Thread>>) violations) {
      errors += violation.getMessage() + ";";
    }

    return errors;
  }
  
}