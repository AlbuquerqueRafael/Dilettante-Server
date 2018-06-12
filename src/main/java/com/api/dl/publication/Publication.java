package com.api.dl.publication;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.dl.user.User;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
  private String name;

  @Column(nullable = false)
  private Type type;

  @Column(nullable = false)
  private User user;

  @Column(nullable = false)
  private Location location;

  @CreationTimestamp
  @Column(name = "created_at")
  public LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  public LocalDateTime updatedAt;

  //Add content info

  protected Publication () {
	}

	public Publication (String name, Type type, User user, Location location) {
    this.name = name;
    this.type = type;
    this.user = user;
    this.location = location;
  }

  public Long getId () {
	  return this.id;
  }

  public String getName () {
	  return this.name;
  }

  public Type getType() {
    return this.type;
  }

	public User getUser () {
	  return this.user;
  }

  public Location location () {
    return this.location;
  }




}