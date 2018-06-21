package com.api.dl.thread;

public enum ThreadType {
  FEELINGS(0), HISTORIAL_FACTS(1), GENERAL_COMMENTS(2);

  private final int value;
  
  private ThreadType(int value) {
      this.value = value;
  }

  public int getValue() {
      return value;
  }

  public String getThreadType() {
    return name();
  }
}