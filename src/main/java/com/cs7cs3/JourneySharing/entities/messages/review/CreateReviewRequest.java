package com.cs7cs3.JourneySharing.entities.messages.review;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class CreateReviewRequest extends Validatable {
  public String userId = "";
  public String reviewId = "";
  public String revieweeId = "";
  public double rating = 0.0f;
  public String content = "";
  public boolean anonymous = true;
}
