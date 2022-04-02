package com.cs7cs3.JourneySharing.entities.messages.token;

public class CheckTokenResponse {
  public static CheckTokenResponse make(boolean available) {
    return new CheckTokenResponse(available);
  }

  public CheckTokenResponse(boolean available) {
    this.available = available;
  }

  boolean available;
}
