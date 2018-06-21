package com.api.dl.publication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.api.dl.publication.exceptions.PublicationNotFoundException;
import com.api.dl.publication.exceptions.PublicationOwnerException;
import com.api.dl.reply.ReplyService;
import com.api.dl.thread.ThreadService;
import com.api.dl.user.User;
import com.api.dl.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class PublicationService {

  @Autowired
  private PublicationRepository publicationRepository;

  @Autowired
  private UserService userService;

  private Map<String, String> response = new HashMap<String, String>();

  public Map<String, String> create (Publication publication) {
    User user = userService.getLoggedUser();
    publication.setUser(user);
    publicationRepository.save(publication);
    
    return response;
  }

  public Map<String, List<Publication>> getPublications () {
    PageRequest request = PageRequest.of(0, 12);
    Page<Publication> page = publicationRepository.findAll(request);
    

    Map<String, List<Publication>> response = new HashMap<String, List<Publication>>();
    response.put("data", page.getContent());
    
    return response;
  }

  public Map<String, Publication> edit (Long id, Publication publication) {
    Optional<Publication> optPublication = publicationRepository.findById(id);
    Map<String, Publication> response = new HashMap<String, Publication>();

    if (!optPublication.isPresent()) {
      throw new PublicationNotFoundException("Publication not found;");
    }

    Publication auxPublication = optPublication.get();
    User user = userService.getLoggedUser();

    if (!auxPublication.getUser().equals(user)) {
      throw new PublicationOwnerException("You are not the owner of this publication");
    }

    publication.setId(auxPublication.getId());
    publication.setUser(user);
    publicationRepository.save(publication);
    
    response.put("data", publication);

    return response;
  }

  public Map<String, Publication> getPublication(Long id) {
    Map<String, Publication> response = new HashMap<String, Publication>();
    Publication publication = getPublicationByID(id);
    response.put("data", publication);
    
    return response;
  }

  public Publication getPublicationByID (Long id) {
    Optional<Publication> optPublication = publicationRepository.findById(id);

    if (!optPublication.isPresent()) {
      throw new PublicationNotFoundException("Publication not found;");
    }

    return optPublication.get();
  }
}