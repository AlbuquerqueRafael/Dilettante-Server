package com.api.dl.search;

import java.util.Map;

import com.api.dl.publication.Location;
import com.api.dl.publication.Type;
import com.api.dl.search.dto.PublicationSearchDTO;

import org.springframework.stereotype.Service;


@Service
public class SearchService {

  public void search(Map<String, String[]> requestParameterMap) {
    PublicationSearchDTO publicationSearchDTO = mapToDTO(requestParameterMap);
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