package com.api.dl.publication;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.api.dl.publication.exceptions.InvalidPublicationException;
import com.api.dl.publication.Location;

import org.springframework.stereotype.Service;

@Service
public class PublicationValidator {
  private ValidatorFactory factory;
  private Validator validator;
  
  @PostConstruct
  void started() {
    this.factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }
  
  public void validateCreateAndEdit(Publication publication) {
    Set<ConstraintViolation<Publication>> publicationViolations = validator.validate(publication);
    String errors = "";

    errors += getErrors(publicationViolations, errors);

    if (publication.getLocation() != null) {
      Set<ConstraintViolation<Location>> locationViolations = validator.validate(publication.getLocation());
      errors = getErrors(locationViolations, errors);
    }

    if (publication.getContent() != null) {
      Set<ConstraintViolation<Content>> contentViolations = validator.validate(publication.getContent());
      errors = getErrors(contentViolations, errors);
    }

  
    if (!errors.equals("")) {
      throw new InvalidPublicationException(errors);
    }
  }

  private String getErrors(Set violations, String errors) {
    for (ConstraintViolation violation : (Set<ConstraintViolation>) violations) {
      errors += violation.getMessage() + ";";
    }

    return errors;
  }
  
}