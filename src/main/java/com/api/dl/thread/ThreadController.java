package com.api.dl.thread;

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
@RequestMapping(value = "/publication")
public class ThreadController {

  @Autowired
  ThreadService threadService;

  @Autowired
  ThreadValidator threadValidator;

  @RequestMapping(value = "/{id}/thread", method = RequestMethod.POST)
  public Map<String, Thread> save (@PathVariable("id") Long publicationID, 
                                   @RequestBody Thread thread) {

    
    threadValidator.validateSave(thread);                              
    Map<String, Thread> response = threadService.save(publicationID, thread);

		return response;
  }


  @RequestMapping(value = "{id}/thread", method = RequestMethod.GET)
  public Map<String, List<Thread>> getThreadsByPublication (@PathVariable("id") Long publicationID) {
    Map<String, List<Thread>> response = threadService.getThreadsByPublication(publicationID);

		return response;
  }

  
}