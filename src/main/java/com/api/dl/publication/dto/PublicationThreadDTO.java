package com.api.dl.publication.dto;

import com.api.dl.reply.Reply;
import java.util.List;


public class PublicationThreadDTO {

  List<Reply> replies;

  protected PublicationThreadDTO () {
	}

	public PublicationThreadDTO (List<Reply> replies) {
    this.replies = replies;
  }

  public List<Reply> getReplies () {
    return this.replies;
  }

}