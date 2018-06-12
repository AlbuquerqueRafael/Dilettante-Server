package com.api.dl.publication;

public enum Type {
  POETRY, PAINTING, SCULPTURE, STREET_ART, COMICS, GENERAL;

  public String getType() {
    return name();
  }
}