package com.cs7cs3.JourneySharing.entities.messages.account;

import com.cs7cs3.JourneySharing.entities.Gender;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class RegisterRequest extends Validatable {
  public String username = "";
  public String password = "";
  public Gender gender = Gender.other;
  public String publicKey = "";
  public String privateKey = "";
  public long timestamp = 0l;
}
