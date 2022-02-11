package com.cs7cs3.JourneySharing.entities.request;

import com.cs7cs3.JourneySharing.entities.Location;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class CreateJourneyRequest extends Validatable {
  public String userId = "";
  public Location from = new Location();
  public Location to = new Location();
}
