package com.api.dl.publication;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
@Table (name = "location")
public class Location implements Serializable {

  private static final long serialVersionUID = -1513339037567362L;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @Column()
  private String additionalInfo;

  //Maybe I will normalize in future
  @Column(nullable = false)
  @NotBlank(message="Please, inform a valid city")
  private String city;

  @Column(nullable = false)
  @NotBlank(message="Please, inform a valid state")
  private String state;

  @Column(nullable = false)
  @NotBlank(message="Please, inform a valid country")
  private String country;

  @CreationTimestamp
  @Column(name = "created_at")
  @JsonProperty(access = Access.WRITE_ONLY)
  public LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  @JsonProperty(access = Access.WRITE_ONLY)
  public LocalDateTime updatedAt;

  public Location () {
	}

	public Location (String additionalInfo, String country, String state, String city) {
    this.additionalInfo = additionalInfo;
    this.country = country;
    this.state = state;
    this.city = city;
  }

  public String getAdditionalInfo() {
    return this.additionalInfo;
  }

  public String getCountry() {
    return this.country;
  }

  public String getState() {
    return this.state;
  }

  public String getCity() {
    return this.city;
  }


  public void setCountry(String country) {
    this.country = country;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setCity(String city) {
    this.city = city;
  }

}