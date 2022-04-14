package com.cs7cs3.JourneySharing.entities.messages.journey;

import java.util.List;

import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class GetJourneyByLocationResponse extends Validatable {
  public static GetJourneyByLocationResponse make(List<Journey> journeys) {
    return new GetJourneyByLocationResponse(journeys);
  }

  private GetJourneyByLocationResponse(List<Journey> journeys) {
    this.journeys = journeys;
  }

  public List<Journey> journeys;
}
