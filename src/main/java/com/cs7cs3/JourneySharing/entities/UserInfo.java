package com.cs7cs3.JourneySharing.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.cs7cs3.JourneySharing.entities.messages.account.RegisterRequest;

import lombok.Data;

@Data
@Entity
@Table(name = "user_info")
public class UserInfo extends Validatable {

  public static UserInfo make(Account account, RegisterRequest req) {
    UserInfo userInfo = new UserInfo();

    userInfo.id = account.id;
    userInfo.username = req.username;

    return userInfo;
  }

  public static UserInfo make2(String id, String username, String avatarUrl, String boi) {
    UserInfo userInfo = new UserInfo();

    userInfo.id = id;
    userInfo.username = username;
    userInfo.avatarUrl = avatarUrl;
    userInfo.bio = boi;

    return userInfo;
  }

  @Id
  public String id = "";
  public String username = "";
  public String avatarUrl = "";
  public String bio = "";
  public double rating = 0.0;

  @ElementCollection
  @Column(name = "review_id")
  public List<String> reviews = new ArrayList<String>();

  @ElementCollection
  @Column(name = "journey_id")
  public List<String> histories = new ArrayList<String>();
}