package com.cs7cs3.JourneySharing.entities.messages.journey;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class ExitJourneyResponse extends Validatable {
  public static ExitJourneyResponse make() {
    return null;
  }

  private ExitJourneyResponse() {
  }
}
