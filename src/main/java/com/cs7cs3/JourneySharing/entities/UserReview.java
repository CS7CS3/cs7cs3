package com.cs7cs3.JourneySharing.entities;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class UserReview extends Validatable {
  public String reviewId = "";
  public String userId = "";
  public boolean anonymous = true;
  public double rating = 0.0;
  public String content = "";
}
