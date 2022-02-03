package com.cs7cs3.JourneySharing.entities;

import java.util.ArrayList;
import java.util.List;

import com.cs7cs3.JourneySharing.entities.base.JourneyId;
import com.cs7cs3.JourneySharing.entities.base.ReviewId;
import com.cs7cs3.JourneySharing.entities.base.Url;
import com.cs7cs3.JourneySharing.entities.base.UserId;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class UserInfo extends Validatable{
  public UserId userId = new UserId();
  public Url avatarUrl = new Url();
  public String username = "";
  public String bio = "";
  public double rating = 0.0;
  public List<ReviewId> reviews = new ArrayList<ReviewId>();
  public List<JourneyId> histories = new ArrayList<JourneyId>();
}