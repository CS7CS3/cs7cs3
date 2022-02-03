package com.cs7cs3.JourneySharing.entities;

import java.util.ArrayList;
import java.util.List;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class UserInfo extends Validatable{
  public String userId = "";
  public String avatarUrl = "";
  public String username = "";
  public String bio = "";
  public double rating = 0.0;
  public List<String> reviews = new ArrayList<String>();
  public List<String> histories = new ArrayList<String>();
}