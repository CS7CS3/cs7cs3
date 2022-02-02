package com.cs7cs3.JourneySharing.entities;

import com.cs7cs3.JourneySharing.entities.base.Timestamp;
import com.cs7cs3.JourneySharing.entities.base.UserId;

import lombok.Data;

@Data
public class LoginRequest {
  UserId userId = new UserId();
  public String password = "";
  public Timestamp timestamp = new Timestamp();

  public String getUserId() {
    return userId.value;
  }

  public void setUserId(String value) {
    userId.value = value;
  }

  public long getTimestamp() {
    return timestamp.value;
  }

  public void setTimestamp(long value) {
    timestamp.value = value;
  }

}
