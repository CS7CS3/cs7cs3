package com.cs7cs3.JourneySharing.entities;

import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.cs7cs3.JourneySharing.entities.messages.account.RegisterRequest;
import com.cs7cs3.JourneySharing.utils.Utils;
import com.github.javafaker.Faker;

import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account extends Validatable {
  public static Faker faker = new Faker(Locale.UK);

  public static Account make(String username, String password) {
    Account acc = new Account();

    acc.id = Utils.makeUUID();
    acc.password = password;

    return acc;
  }

  public static Account makeFake() {
    Account acc = new Account();

    acc.id = Utils.makeUUID();
    acc.password = "123456";

    return acc;
  }

  public static Account make(RegisterRequest req) {
    Account acc = new Account();

    acc.id = Utils.makeUUID();
    acc.password = req.password;
    acc.publicKey = req.publicKey;
    acc.privateKey = req.privateKey;

    return acc;
  }

  @Id
  public String id = "";
  public String password = "";
  public String publicKey = "";
  public String privateKey = "";

  @Override
  public boolean validate() {
    return id != "" && password != "";
  }

}
