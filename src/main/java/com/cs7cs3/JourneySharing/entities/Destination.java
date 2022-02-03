package com.cs7cs3.JourneySharing.entities;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class Destination extends Validatable{
  public double latitude = 0.0;
  public double longitude = 0.0;
}
