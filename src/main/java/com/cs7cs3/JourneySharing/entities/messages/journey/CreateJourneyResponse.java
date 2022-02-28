package com.cs7cs3.JourneySharing.entities.messages.journey;

import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class CreateJourneyResponse extends Validatable {
  public static CreateJourneyResponse make(Journey journey) {
    return new CreateJourneyResponse(journey);
  }

  private CreateJourneyResponse(Journey journey) {
    this.journey = journey;
  }

  public Journey journey;

}
