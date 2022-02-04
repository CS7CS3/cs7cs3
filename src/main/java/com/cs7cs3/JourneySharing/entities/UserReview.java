package com.cs7cs3.JourneySharing.entities;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class UserReview extends Validatable {
  @Id
  public String reviewId = "";

  @Column(name = "user_id")
  public String userId = "";
  public boolean anonymous = true;
  public double rating = 0.0;
  public String content = "";

}
