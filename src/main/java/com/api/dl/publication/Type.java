package com.api.dl.publication;

public enum Type {
  POETRY(0), PAINTING(1), SCULPTURE(2), STREET_ART(3), COMICS(4), GENERAL(5);

  private final int value;
  
  private Type(int value) {
      this.value = value;
  }

  public int getValue() {
      return value;
  }

  public String getType() {
    return name();
  }
}