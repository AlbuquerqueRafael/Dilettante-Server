package com.api.dl.publication;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class PublicationController {
	
  @Autowired
  private PublicationService publicationService;

  @Autowired
  private PublicationValidator publicationValidator;

  
  @RequestMapping(value = "/publication/{id}", method = RequestMethod.PUT)
  public Map<String, Publication> edit (@PathVariable("id") Long id, @RequestBody Publication publication) {
    publicationValidator.validateCreateAndEdit(publication);                              
    Map<String, Publication> response = publicationService.edit(id, publication);

		return response;
  }

  @RequestMapping(value = "/publication", method = RequestMethod.POST)
  public Map<String, String> create (@RequestBody Publication publication) {
    publicationValidator.validateCreateAndEdit(publication);                              
    Map<String, String> response = publicationService.create(publication);

		return response;
  }

  @RequestMapping(value = "/publication", method = RequestMethod.GET)
  public Map<String, List<Publication>> getPublications () {
    Map<String, List<Publication>> response = publicationService.getPublications();

		return response;
  }

  @RequestMapping(value = "/publication/{id}", method = RequestMethod.GET)
  public Map<String, Publication> getPublication (@PathVariable("id") Long id) {
    Map<String, Publication> response = publicationService.getPublication(id);

		return response;
  }
  
}