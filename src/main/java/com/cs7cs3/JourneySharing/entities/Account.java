package com.cs7cs3.JourneySharing.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.cs7cs3.JourneySharing.entities.messages.account.RegisterRequest;
import com.cs7cs3.JourneySharing.utils.Utils;

import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account extends Validatable {

  public static Account make(String username, String password) {
    Account acc = new Account();

    acc.id = Utils.makeUUID();
    acc.password = password;

    return acc;
  }

  public static Account make(RegisterRequest req) {
    Account acc = new Account();

    acc.id = Utils.makeUUID();
    acc.password = req.password;

    return acc;
  }

  @Id
  public String id = "";
  public String password = "";

  @Override
  public boolean validate() {
    return id != "" && password != "";
  }

}
