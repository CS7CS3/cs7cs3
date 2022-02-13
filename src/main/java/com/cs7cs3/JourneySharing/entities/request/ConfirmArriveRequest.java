package com.cs7cs3.JourneySharing.entities.request;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

public class ConfirmArriveRequest extends Validatable {
  String userId;
  String journeyId;
}
