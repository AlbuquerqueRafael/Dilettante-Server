package com.api.dl.reply;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.api.dl.thread.Thread;
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
@Table (name = "reply")
public class Reply implements Serializable {

  private static final long serialVersionUID = -1514037567362L;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message="Please, inform a comment")
  private String comment;

  @ManyToOne(optional=false)
  private Thread thread;

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

  protected Reply () {
	}

	public Reply (String comment, User user, Thread thread) {
    this.comment = comment;
    this.user = user;
    this.thread = thread;
  }

  public String getComment () {
    return this.comment;
  }

  public String getUser () {
    return this.user.getUsername();
  }


}