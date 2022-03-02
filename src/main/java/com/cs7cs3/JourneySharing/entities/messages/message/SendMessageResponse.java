package com.cs7cs3.JourneySharing.entities.messages.message;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class SendMessageResponse extends Validatable {
  public static SendMessageResponse make() {
    return null;
  }

  private SendMessageResponse() {
  }
}
