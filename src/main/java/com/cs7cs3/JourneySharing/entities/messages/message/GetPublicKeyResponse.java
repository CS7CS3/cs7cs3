package com.cs7cs3.JourneySharing.entities.messages.message;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

public class GetPublicKeyResponse extends Validatable {
  public static GetPublicKeyResponse make(String publicKey) {
    return new GetPublicKeyResponse(publicKey);
  }

  public GetPublicKeyResponse(String publicKey) {
    this.publicKey = publicKey;
  }

  public String publicKey;
}
