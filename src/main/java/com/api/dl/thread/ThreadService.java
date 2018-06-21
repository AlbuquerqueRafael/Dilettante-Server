package com.api.dl.thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.api.dl.publication.Publication;
import com.api.dl.publication.PublicationService;
import com.api.dl.thread.exceptions.ThreadNotFoundException;
import com.api.dl.user.User;
import com.api.dl.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ThreadService {

  @Autowired
  private PublicationService publicationService;

  @Autowired
  private UserService userService;

  @Autowired
  ThreadRepository threadRepository;


  private Map<String, Thread> response = new HashMap<String, Thread>();

  public Map<String, Thread> save (Long publicationID, Thread thread) {
    Publication publication = publicationService.getPublicationByID(publicationID);
    User user = userService.getLoggedUser();

    Thread pThread = new Thread(thread.getComment(), user, publication, thread.getThreadType());
    threadRepository.save(pThread);
    
    response.put("data", pThread);

    return response;
  }

  public Thread getThreadByID (Long id) {
    Optional<Thread> optThread = threadRepository.findById(id);

    if (!optThread.isPresent()) {
      throw new ThreadNotFoundException("Thread not found;");
    }

    return optThread.get();
  }

  public Map<String, List<Thread>> getThreadsByPublication (Long publicationID) {
    Publication publication = publicationService.getPublicationByID(publicationID);
    List<Thread> threads = threadRepository.findByPublication(publication);

    Map<String, List<Thread>> response = new HashMap<String, List<Thread>>();

    response.put("data", threads);

    return response;
  }

}