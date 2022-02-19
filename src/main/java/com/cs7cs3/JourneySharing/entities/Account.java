package com.cs7cs3.JourneySharing.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.cs7cs3.JourneySharing.entities.request.RegisterRequest;

import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account extends Validatable {

  public static Account make(String username, String password) {
    Account acc = new Account();

    acc.id = UUID.randomUUID().toString();
    acc.password = password;

    return acc;
  }

  public static Account make(RegisterRequest req) {
    Account acc = new Account();

    acc.id = UUID.randomUUID().toString();
    acc.password = req.password;

    return acc;
  }

  @Id
  public String id = "";
  public String password = "";

}
