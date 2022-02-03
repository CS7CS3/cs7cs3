package com.cs7cs3.JourneySharing.entities.base;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class UserId extends Validatable{
  public String value = "";
}
