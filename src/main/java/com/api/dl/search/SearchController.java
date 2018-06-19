package com.api.dl.search;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class SearchController {

  @Autowired
  private SearchValidator searchValidator;


  /**
   * @param option - Search option : Publication or User
   * @param p - Page Number
   * @param timeFilter - The type of time filter
   * 0 - Publications from current month
   * 1 - Publications from current year
   * 2 - Publication without date filter
   * @param state - Optional: State of the publication
   * @param country - Optional: Country of the publication
   * @param city - Optional: City of the publication
   * @param type - Optional: Type of the publication
   */
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public Map<String, String> search (HttpServletRequest httpServletRequest) {
    Map<String, String[]> requestParameterMap = httpServletRequest.getParameterMap();
    searchValidator.validateSearch(requestParameterMap);

    Map<String, String> response = new HashMap<>();

		return response;
  }


}