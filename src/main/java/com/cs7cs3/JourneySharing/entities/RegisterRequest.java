package com.cs7cs3.JourneySharing.entities;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class RegisterRequest extends Validatable {
  public String userId = "";
  public String password = "";
  public long timestamp = 0l;
  public String email = "";
}
