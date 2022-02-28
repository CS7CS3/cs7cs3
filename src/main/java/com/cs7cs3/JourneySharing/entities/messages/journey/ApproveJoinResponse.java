package com.cs7cs3.JourneySharing.entities.messages.journey;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class ApproveJoinResponse extends Validatable {
  public static ApproveJoinResponse make() {
    return null;
  }

  private ApproveJoinResponse() {
  }
}
