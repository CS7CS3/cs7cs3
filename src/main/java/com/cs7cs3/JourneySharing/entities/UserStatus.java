package com.cs7cs3.JourneySharing.entities;

public enum UserStatus {
  PendingApproval, Waiting, Travelling, Arrived;

  private static UserStatus[] values = null;

  public static UserStatus cast(int i) {
    if (values == null) {
      values = UserStatus.values();
    }
    return values[i];
  }
}