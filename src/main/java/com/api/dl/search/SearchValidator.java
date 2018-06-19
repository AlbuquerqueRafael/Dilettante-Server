package com.api.dl.search;

import java.util.Map;

import com.api.dl.publication.Type;
import com.api.dl.publication.exceptions.InvalidPublicationException;
import com.api.dl.search.exceptions.InvalidSearchException;

import org.springframework.stereotype.Service;

@Service
public class SearchValidator {
  
  public void validateSearch (Map<String, String[]> parameters) {
    if (parameters.get("p") == null || parameters.get("p")[0] == null ||
        parameters.get("p")[0].trim().equals("")) {
      throw new InvalidSearchException("Please, inform a valid page value");
    } else if (parameters.get("option") == null || parameters.get("option")[0] == null ||
               (!parameters.get("option")[0].equals("publication"))) {
      throw new InvalidSearchException("Please, inform a valid option");
    } else if (parameters.get("option")[0].equals("publication")) {
      validatePublicationSearch(parameters);
    } 

    try {
      new Integer( parameters.get("p")[0]);
    } catch (NumberFormatException e) {
      throw new InvalidSearchException("Please, inform a valid page value");
    }
  }

  private void validatePublicationSearch (Map<String, String[]> parameters) {
    if (parameters.get("timeFilter") == null) {
      throw new InvalidPublicationException("Please, inform a valid time filter");
    } else {
      validateTimeFilter(parameters.get("timeFilter")[0]);
    }
    
    if (parameters.get("type") != null) { 
      validateType(parameters.get("type")[0]);
    }
  }

  private void validateTimeFilter(String timeFilter) {
    try {
      int tmeFilter = new Integer(timeFilter);
      TimeFilter timeFilterEnum = TimeFilter.values()[tmeFilter];
    } catch (NumberFormatException e) {
      throw new InvalidPublicationException("Please, inform a valid time filter");
    } catch (ArrayIndexOutOfBoundsException e ) {
      throw new InvalidPublicationException("Please, inform a valid time filter");
    }
  }

  private void validateType(String type) {
    try {
      int tpe = new Integer(type);
      Type typeEnum = Type.values()[tpe];
    } catch (NumberFormatException e) {
      throw new InvalidPublicationException("Please, inform a valid publication type");
    } catch (ArrayIndexOutOfBoundsException e ) {
      throw new InvalidPublicationException("Please, inform a valid publication type");
    }
  }

}