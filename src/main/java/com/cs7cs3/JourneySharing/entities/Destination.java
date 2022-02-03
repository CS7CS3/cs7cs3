package com.cs7cs3.JourneySharing.entities;

import javax.persistence.Embeddable;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
@Embeddable
public class Destination extends Validatable{
  public double latitude = 0.0;
  public double longitude = 0.0;
}
