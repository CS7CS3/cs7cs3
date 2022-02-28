package com.cs7cs3.JourneySharing.entities.messages.journey;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class StartRequest extends Validatable {
  public String hostId;
}
