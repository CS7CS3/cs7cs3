package com.cs7cs3.JourneySharing.entities.messages.message;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class GetPublicKeyRequest extends Validatable {
  public String userId;
}
