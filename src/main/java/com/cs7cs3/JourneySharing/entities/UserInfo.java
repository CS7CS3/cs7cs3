package com.cs7cs3.JourneySharing.entities;

import java.util.ArrayList;
import java.util.List;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserInfo extends Validatable{
  @Id
  public String userId = "";

  @Column(name = "avatar_url")
  public String avatarUrl = "";
  public String username = "";
  public String bio = "";
  public double rating = 0.0;

  @ElementCollection
  public List<String> reviews = new ArrayList<String>();
  @ElementCollection
  public List<String> histories = new ArrayList<String>();
}