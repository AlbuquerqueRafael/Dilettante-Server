package com.api.dl.publication;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table (name = "content")
public class Content implements Serializable {

  private static final long serialVersionUID = -1513339037567362L;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private String description;

  @Column(nullable = false)
  @NotNull(message="Please, inform a valid content type")
  private Type type;

  @Column(nullable = true)
  private String link;

  @Column(nullable = true)
  private String blobName;

  @CreationTimestamp
  @Column(name = "created_at")
  @JsonProperty(access = Access.WRITE_ONLY)
  public LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  @JsonProperty(access = Access.WRITE_ONLY)
  public LocalDateTime updatedAt;

  @Column()
  public String author;

  protected Content () {
	}

	public Content (Type type, String link, String description, String author) {
    this.type = type;
    this.link = link;
    this.description = description;
    this.author = author;
  }

  public Type getType () {
    return this.type;
  }

  public String getLink () {
    return this.link;
  }

  public String getBlobName () {
    return this.blobName;
  }

  public String getDescription () {
    return this.description;
  }

  public String getAuthor () {
    return this.author;
  }

  public void setLink (String link) {
    this.link = link;
  }

  public void setBlobName (String blobName) {
    this.blobName = blobName;
  }
}