package com.api.dl.reply;

import java.util.HashMap;
import java.util.Map;

import com.api.dl.thread.Thread;
import com.api.dl.thread.ThreadService;
import com.api.dl.user.User;
import com.api.dl.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReplyService {

  @Autowired
  private ThreadService threadService;

  @Autowired
  private UserService userService;

  @Autowired
  ReplyRepository replyRepository;


  private Map<String, Reply> response = new HashMap<String, Reply>();

  public Map<String, Reply> save (Long threadID, Reply reply) {
    Thread thread = threadService.getThreadByID(threadID);
    User user = userService.getLoggedUser();

    Reply pReply = new Reply(reply.getComment(), user, thread);
    replyRepository.save(pReply);
    
    response.put("data", pReply);

    return response;
  }

}