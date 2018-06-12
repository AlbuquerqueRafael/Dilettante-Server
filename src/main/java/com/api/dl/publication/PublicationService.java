package com.api.dl.publication;

import java.util.HashMap;
import java.util.Map;

import com.api.dl.security.JWTTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PublicationService {

  @Autowired
  private PublicationRepository publicationRepository;

  @Autowired
  private JWTTokenProvider jwtTokenProvider;

  private Map<String, String> response = new HashMap<String, String>();

  public Map<String, String> create (Publication publication) {
    publicationRepository.save(publication);
    
    return response;
  }



  
}