package com.cs7cs3.JourneySharing.entities.messages.message;

import java.util.List;

import com.cs7cs3.JourneySharing.entities.Message;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class GetMessageResponse extends Validatable {
  public static GetMessageResponse make(List<Message> messages) {
    return new GetMessageResponse(messages);
  }

  private GetMessageResponse(List<Message> messages) {
    this.messages = messages;
  }

  List<Message> messages;
}
