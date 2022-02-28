package com.cs7cs3.JourneySharing.entities.messages.journey;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class ConfirmArriveResponse extends Validatable {
  public static ConfirmArriveResponse make() {
    return null;
  }

  private ConfirmArriveResponse() {
  }
}
