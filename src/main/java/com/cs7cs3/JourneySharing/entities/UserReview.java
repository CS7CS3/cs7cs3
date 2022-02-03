package com.cs7cs3.JourneySharing.entities;

import com.cs7cs3.JourneySharing.entities.base.ReviewId;
import com.cs7cs3.JourneySharing.entities.base.UserId;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class UserReview extends Validatable {
  public ReviewId reviewId = new ReviewId();
  public UserId userId = new UserId();
  public boolean anonymous = true;
  public double rating = 0.0;
  public String content = "";
}
