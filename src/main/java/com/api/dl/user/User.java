package com.api.dl.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.ElementCollection;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "dl_user")
public class User implements Serializable {

  private static final long serialVersionUID = -1785131699037567362L;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique=true)
  @NotBlank(message="Please, inform a valid username")
  private String username;

  @Column(nullable = false)
  @JsonProperty(access = Access.WRITE_ONLY)
  @NotBlank(message="Please, inform a valid password")
  private String password;

  @Column(nullable = false)
  @NotBlank(message="Please, inform a valid email")
  private String email;

  @Column(nullable = false)
  @JsonProperty(access = Access.WRITE_ONLY)
  private boolean active = false;

  @Column(nullable = false)
  @NotNull(message="Please, inform a valid birth date")
  private LocalDate birthDate;

  @CreationTimestamp
  @Column(name = "created_at")
  @JsonProperty(access = Access.WRITE_ONLY)
  public LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  @JsonProperty(access = Access.WRITE_ONLY)
  public LocalDateTime updatedAt;

  @ElementCollection(fetch = FetchType.EAGER)
  @JsonProperty(access = Access.WRITE_ONLY)
  List<Role> roles;

  protected User () {
  }
  
  public User (String username, String password) {
    this.username = username;
    this.password = password;
  }

	public User (String username, String password, List<Role> roles, String email, 
               boolean active, LocalDate birthDate) {
    this.username = username;
    this.password = password;
    this.roles = roles;
    this.email = email;
    this.active = active;
    this.birthDate = birthDate;
  }

  public Long getId () {
	  return this.id;
  }

  public String getUsername () {
	  return this.username;
  }

	public String getPassword () {
	  return this.password;
  }

  public List<Role> getRoles () {
    return this.roles;
  }

  public String getEmail () {
    return this.email;
  }

  public boolean isActive () {
    return this.active;
  }

  public LocalDate getBirthDate () {
    return this.birthDate;
  }

  public void setUsername (String username) {
    this.username = username;
  }

  public void setPassword (String password) {
    this.password = password;
  }

  public void setRoles (List<Role> roles) {
    this.roles = roles;
  }

   public void setEmail(String email) {
    this.email = email;
  }

  public void setActive (boolean active) {
    this.active = active;
  }

  public void setBirthDate (LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  @Override
  public boolean equals (Object auxObj) {
    return true;
  }
}