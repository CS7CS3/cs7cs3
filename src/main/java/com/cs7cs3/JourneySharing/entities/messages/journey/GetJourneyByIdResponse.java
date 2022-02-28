package com.cs7cs3.JourneySharing.entities.messages.journey;

import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class GetJourneyByIdResponse extends Validatable {
  public static GetJourneyByIdResponse make(Journey journey) {
    return new GetJourneyByIdResponse(journey);
  }

  private GetJourneyByIdResponse(Journey journey) {
    this.journey = journey;
  }

  public Journey journey;
}