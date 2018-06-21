package com.api.dl.thread;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/publication")
public class ThreadController {

  @Autowired
  ThreadService threadService;

  @Autowired
  ThreadValidator threadValidator;

  @RequestMapping(value = "/{id}/thread", method = RequestMethod.POST)
  public Map<String, Thread> edit (@PathVariable("id") Long publicationID, 
                                   @RequestBody Thread thread) {

    
    threadValidator.validateSave(thread);                              
    Map<String, Thread> response = threadService.save(publicationID, thread);

		return response;
  }

  
}