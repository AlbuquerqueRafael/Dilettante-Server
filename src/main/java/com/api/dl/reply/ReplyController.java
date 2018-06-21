package com.api.dl.reply;

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
@RequestMapping(value = "/api/thread")
public class ReplyController {

  @Autowired
  ReplyService replyService;

  @Autowired
  ReplyValidator replyValidator;

  @RequestMapping(value = "/{id}/reply", method = RequestMethod.POST)
  public Map<String, Reply> edit (@PathVariable("id") Long threadID, 
                                  @RequestBody Reply reply) {

    
    replyValidator.validateSave(reply);                              
    Map<String, Reply> response = replyService.save(threadID, reply);

		return response;
  }

  
}