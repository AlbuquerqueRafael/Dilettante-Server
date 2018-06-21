package com.api.dl.publication.love;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.api.dl.publication.Publication;
import com.api.dl.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "love")
public class Love implements Serializable {

  private static final long serialVersionUID = -1513337567362L;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @ManyToOne(optional=false)
  private User user;

  @ManyToOne(optional=false)
  private Publication publication;

  @CreationTimestamp
  @Column(name = "created_at")
  @JsonProperty(access = Access.WRITE_ONLY)
  public LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  @JsonProperty(access = Access.WRITE_ONLY)
  public LocalDateTime updatedAt;

  protected Love () {
	}

	public Love (User user, Publication publication) {
    this.user = user;
    this.publication = publication;
  }

  public String getUser () {
    return this.user.getUsername();
  }

}