package com.api.dl.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api.dl.publication.Location;
import com.api.dl.publication.Publication;
import com.api.dl.publication.PublicationRepository;
import com.api.dl.publication.Type;
import com.api.dl.search.dto.PublicationSearchDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class SearchService {
  @Autowired
  PublicationRepository publicationRepository;

  Map<String, List<Publication>> response = new HashMap<String, List<Publication>>();

  public Map<String, List<Publication>> search(Map<String, String[]> requestParameterMap) {
    PublicationSearchDTO publicationSearchDTO = mapToDTO(requestParameterMap);
    PageRequest request = PageRequest.of(publicationSearchDTO.getPage(), 20);
    PublicationSearchSpecifications pss = new PublicationSearchSpecifications(publicationSearchDTO);
    Page<Publication> page = publicationRepository.findAll(pss, request);
    
    response.put("data", page.getContent());

    return response;
  }


  private PublicationSearchDTO mapToDTO(Map<String, String[]> requestParameterMap) {
    int tmeFilter = new Integer(requestParameterMap.get("timeFilter")[0]);
    int page =  new Integer(requestParameterMap.get("p")[0]);
    TimeFilter timeFilterEnum = TimeFilter.values()[tmeFilter];
    PublicationSearchDTO publicationSearchDTO = new PublicationSearchDTO(page,timeFilterEnum);
    publicationSearchDTO.setLocation(getLocation(requestParameterMap));
    publicationSearchDTO.setLocation(getLocation(requestParameterMap));
    fillPublicationType(requestParameterMap, publicationSearchDTO);

    return publicationSearchDTO;
  }

  private Location getLocation (Map<String, String[]> requestParameterMap) {
    Location location = new Location();

    if (requestParameterMap.get("state") != null) {
      location.setState(requestParameterMap.get("state")[0]);
    }

    if (requestParameterMap.get("country") != null) {
      location.setCity(requestParameterMap.get("country")[0]);
    }

    if (requestParameterMap.get("city") != null) {
      location.setCity(requestParameterMap.get("city")[0]);
    }

    return location;
  }

  private void fillPublicationType(Map<String, String[]> requestParameterMap, 
                                   PublicationSearchDTO publicationSearchDTO) {
    if (requestParameterMap.get("type") != null) {
      int typeInt = new Integer(requestParameterMap.get("type")[0]);
      Type type = Type.values()[typeInt];
      publicationSearchDTO.setType(type);
    }
  }

  
}