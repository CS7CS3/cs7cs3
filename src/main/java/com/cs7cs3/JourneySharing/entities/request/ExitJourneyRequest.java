package com.cs7cs3.JourneySharing.entities.request;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class ExitJourneyRequest extends Validatable {
  public String userId;
  public String journeyId;
}
