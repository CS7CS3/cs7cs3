package com.cs7cs3.JourneySharing.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.cs7cs3.JourneySharing.entities.request.RegisterRequest;

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

  @Id
  public String id = "";
  public String username = "";
  public String avatarUrl = "";
  public String bio = "";
  public double rating = 0.0;

  // 表示用户当前的状态
  public Journey.MemberStatus status = Journey.MemberStatus.NotInAGroup;

//  @ElementCollection
//  @Column(name = "review_id")
//  public List<String> reviews = new ArrayList<String>();
//
//  @ElementCollection
//  @Column(name = "journey_id")
//  public List<String> histories = new ArrayList<String>();
}