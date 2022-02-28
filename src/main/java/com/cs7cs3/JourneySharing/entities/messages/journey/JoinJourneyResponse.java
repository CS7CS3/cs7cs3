package com.cs7cs3.JourneySharing.entities.messages.journey;

import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class JoinJourneyResponse extends Validatable {
  public static JoinJourneyResponse make(Journey journey) {
    return new JoinJourneyResponse(journey);
  }

  private JoinJourneyResponse(Journey journey) {
    this.journey = journey;
  }

  public Journey journey;
}
