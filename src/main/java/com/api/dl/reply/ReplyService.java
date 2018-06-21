package com.api.dl.reply;

import java.util.HashMap;
import java.util.List;
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

  public Map<String, List<Reply>> getRepliesByThread (Long threadID) {
    Thread thread = threadService.getThreadByID(threadID);
    List<Reply> threads = replyRepository.findByThread(thread);

    Map<String, List<Reply>> response = new HashMap<String, List<Reply>>();

    response.put("data", threads);

    return response;
  }

}