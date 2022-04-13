package com.cs7cs3.JourneySharing.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.cs7cs3.JourneySharing.entities.messages.account.RegisterRequest;
import com.github.javafaker.Faker;

import lombok.Data;

@Data
@Entity
@Table(name = "user_info")
public class UserInfo extends Validatable {
  public static Faker faker = new Faker(Locale.UK);

  public static UserInfo makeFake(Account account) {
    UserInfo userInfo = new UserInfo();

    userInfo.id = account.id;
    userInfo.username = faker.name().username();

    return userInfo;
  }

  public static UserInfo make(Account account, RegisterRequest req) {
    UserInfo userInfo = new UserInfo();

    userInfo.id = account.id;
    userInfo.username = req.username;

    return userInfo;
  }

  public static UserInfo make(String id, String username, String avatar, String bio) {
    UserInfo userInfo = new UserInfo();

    userInfo.id = id;
    userInfo.username = username;
    userInfo.avatar = avatar;
    userInfo.bio = bio;

    return userInfo;
  }

  public static UserInfo makeRating(String id, String username, String avatar, String bio, Integer counter,
      double rating) {
    UserInfo userInfo = new UserInfo();

    userInfo.id = id;
    userInfo.rating = rating;
    userInfo.counter = counter;
    userInfo.username = username;
    userInfo.avatar = avatar;
    userInfo.bio = bio;

    return userInfo;
  }

  @Id
  public String id = "";
  public String username = "";
  public String avatar = "";
  public String bio = "";
  public Gender gender = Gender.other;
  public double rating = 0.0;
  public int counter = 0;

  @Column(name = "review_id")
  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "user_id"))
  public List<String> reviews = new ArrayList<String>();

  @Column(name = "journey_id")
  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "user_id"))
  public List<String> histories = new ArrayList<String>();

  @Override
  public boolean validate() {
    return id != "";
  }
}