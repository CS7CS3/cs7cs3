package com.cs7cs3.JourneySharing.entities.messages.journey;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class StartJourneyResponse extends Validatable {
  public static StartJourneyResponse make() {
    return null;
  }

  private StartJourneyResponse() {
  }
}
