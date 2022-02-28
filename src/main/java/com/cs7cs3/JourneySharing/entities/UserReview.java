package com.cs7cs3.JourneySharing.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
public class UserReview extends Validatable {
  static public UserReview make(@NonNull String userId, @NonNull String revieweeId, double rating, @NonNull String content) {
    var review = new UserReview();

    review.userId = userId;
    review.revieweeId = revieweeId;
    review.rating = rating;
    review.content = content;


    return review;
  }

  @Id
  public String reviewId = UUID.randomUUID().toString();
  public String userId = "";
  public boolean anonymous = true;
  public double rating = 0.0;
  public String content = "";
  public String revieweeId = "";

}
