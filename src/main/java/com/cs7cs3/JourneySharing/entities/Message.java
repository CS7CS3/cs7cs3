package com.cs7cs3.JourneySharing.entities;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.cs7cs3.JourneySharing.entities.messages.message.SendMessageRequest;
import com.cs7cs3.JourneySharing.utils.Utils;

import lombok.Data;

@Data
@Entity
@Table(name = "message")
public class Message extends Validatable {
  public static Message make(SendMessageRequest req) {
    return new Message(req.sender, req.receiver, req.content);
  }

  public Message() {
  }

  public Message(String sender, String receiver, String content) {
    id = Utils.makeUUID();
    timestamp = Instant.now().getEpochSecond();
    this.sender = sender;
    this.receiver = receiver;
    this.content = content;
  }

  @Id
  public String id;
  public String sender;
  public String receiver;
  public String content;
  public long timestamp;
}
