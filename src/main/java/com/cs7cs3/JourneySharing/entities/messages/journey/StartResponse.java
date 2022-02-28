package com.cs7cs3.JourneySharing.entities.messages.journey;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class StartResponse extends Validatable {
  public static StartResponse make() {
    return null;
  }

  private StartResponse() {
  }
}
