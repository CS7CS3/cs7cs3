package com.cs7cs3.JourneySharing.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.cs7cs3.JourneySharing.utils.Utils;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "user_review")
public class UserReview extends Validatable {

    public static UserReview make(@NonNull String userId, @NonNull String revieweeId, double rating,
                                  @NonNull String content, @NonNull Integer counter) {
    var review = new UserReview();

    review.userId = userId;
    review.revieweeId = revieweeId;
    review.rating = rating;
    review.content = content;
    review.counter = counter;

    return review;
  }

  @Id
  public String id = Utils.makeUUID();
  public String userId = "";
  public String revieweeId = "";
  public boolean anonymous = true;
  public double rating = 0.0;
  public String content = "";
  public int counter = 0;

  @Override
  public boolean validate() {
    return id != "";
  }
}
