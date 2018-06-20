package com.api.dl.publication;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.api.dl.publication.Location;
import com.api.dl.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "publication")
public class Publication implements Serializable {

  private static final long serialVersionUID = -178513339037567362L;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message="Please, inform a valid name")
  private String name;

  @NotNull(message="Please, inform a valid content")
  @OneToOne(cascade = CascadeType.ALL)
  private Content content;

  @ManyToOne(cascade = CascadeType.ALL)
  private User user;

  @NotNull(message="Please, inform a valid location")
  @ManyToOne(cascade = CascadeType.ALL)
  private Location location;

  @CreationTimestamp
  @Column(name = "created_at")
  @JsonProperty(access = Access.WRITE_ONLY)
  public LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  @JsonProperty(access = Access.WRITE_ONLY)
  public LocalDateTime updatedAt;

  //Add content info

  protected Publication () {
	}

	public Publication (String name, Content content, User user, Location location) {
    this.name = name;
    this.content = content;
    this.user = user;
    this.location = location;
  }

  public Long getId () {
	  return this.id;
  }

  public String getName () {
	  return this.name;
  }

  public Content getContent () {
    return this.content;
  }

	public User getUser () {
	  return this.user;
  }

  public Location getLocation () {
    return this.location;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public void setUser (User user) {
    this.user = user;
  }

  public void setContent (Content content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return this.name;
  }

}