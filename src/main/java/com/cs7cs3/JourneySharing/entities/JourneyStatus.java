package com.cs7cs3.JourneySharing.entities;

public enum JourneyStatus {
  Waiting, Travelling, End;

  private static JourneyStatus[] values = null;

  public static JourneyStatus cast(int i) {
    if (values == null) {
      values = JourneyStatus.values();
    }
    return values[i];
  }

}
