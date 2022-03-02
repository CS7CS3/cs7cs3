package com.cs7cs3.JourneySharing.entities.messages.account;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class RegisterResponse extends Validatable {
  public static RegisterResponse make() {
    return null;
  }

  private RegisterResponse() {
  }
}
