package com.api.dl.search;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.api.dl.publication.Publication;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@CrossOrigin
public class SearchController {

  @Autowired
  private SearchValidator searchValidator;

  @Autowired
  private SearchService searchService;

  /**
   * @param option - Search option : publication or User(Not implemented)
   * @param p - Page Number
   * @param timeFilter - The type of time filter
   * 0 - Publications from current month
   * 1 - Publications from current year
   * 2 - Publication without date filter
   * @param state - Optional: State of the publication
   * @param country - Optional: Country of the publication
   * @param city - Optional: City of the publication
   * @param type - Optional: Type of the publication
   * POETRY(0), PAINTING(1), SCULPTURE(2), STREET_ART(3), COMICS(4), GENERAL(5);
   * 
   * @param name - Optional: Name of the publication
   */
  @ApiOperation(value = "Faz a busca de publicações no sistema")
  @RequestMapping(value = "/search/", method = RequestMethod.GET)
  public Map<String, List<Publication>> search (HttpServletRequest httpServletRequest) {
    Map<String, String[]> requestParameterMap = httpServletRequest.getParameterMap();
    searchValidator.validateSearch(requestParameterMap);
    Map<String, List<Publication>> response = searchService.search(requestParameterMap);


		return response;
  }


}