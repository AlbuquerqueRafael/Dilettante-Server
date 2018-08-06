package com.api.dl.thread;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@Table (name = "thread")
public class Thread implements Serializable {

  private static final long serialVersionUID = -1513339037567362L;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message="Please, inform a comment")
  private String comment;

  @Column(nullable = false)
  @NotNull(message="Please, inform a thread type")
  private ThreadType threadType;

  @ManyToOne(optional=false)
  private Publication publication;

  @ManyToOne(optional=false)
  private User user;

  @CreationTimestamp
  @Column(name = "created_at")
  @JsonProperty(access = Access.WRITE_ONLY)
  public LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  @JsonProperty(access = Access.WRITE_ONLY)
  public LocalDateTime updatedAt;

  protected Thread () {
	}

	public Thread (String comment, User user, Publication publication, ThreadType threadType) {
    this.comment = comment;
    this.user = user;
    this.publication = publication;
    this.threadType = threadType;
  }

  public Long getId() {
    return this.id;
  }

  public String getComment () {
    return this.comment;
  }

  public String getUser () {
    return this.user.getUsername();
  }

  public ThreadType getThreadType () {
    return this.threadType;
  }

}