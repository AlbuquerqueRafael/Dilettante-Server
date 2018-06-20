package com.api.dl.search;

public enum TimeFilter {
  THIS_MONTH, THIS_YEAR, ALL_TIME;

  public String getTimeFilter() {
    return name();
  }
}