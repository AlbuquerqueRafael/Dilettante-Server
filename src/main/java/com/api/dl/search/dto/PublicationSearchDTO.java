package com.api.dl.search.dto;
import com.api.dl.publication.Location;
import com.api.dl.publication.Type;
import com.api.dl.search.TimeFilter;


public class PublicationSearchDTO {

  //searched page number
  int page;
  Type type;
  Location location;
  TimeFilter timeFilter;

  public PublicationSearchDTO (int page, TimeFilter timeFilter) {
    this.page = page;
    this.timeFilter = timeFilter;
  }

  public int getPage () {
    return this.page;
  }

  public Type getType () {
    return this.type;

  }

  public TimeFilter getTimeFilter () {
    return this.timeFilter;
  }

  public Location getLocation () {
    return this.location;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

}