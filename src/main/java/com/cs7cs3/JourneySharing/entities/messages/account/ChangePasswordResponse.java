package com.cs7cs3.JourneySharing.entities.messages.account;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class ChangePasswordResponse extends Validatable {
  public static ChangePasswordResponse make() {
    return null;
  }

  private ChangePasswordResponse() {
  }
}
