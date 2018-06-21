package com.api.dl.publication.dto;

import com.api.dl.publication.Publication;
import java.util.List;

public class PublicationDTO {

  Publication publication;
  List<Thread> threads;

  protected PublicationDTO () {
	}

	public PublicationDTO (Publication publication, List<Thread> threads) {
    this.publication = publication;
    this.threads = threads;
  }

  public Publication getPublication () {
	  return this.publication;
  }

  public List<Thread> getThreads () {
    return this.threads;
  }

}