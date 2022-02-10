package com.cs7cs3.JourneySharing.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
@Entity
public class Account extends Validatable {
  @Id
  public String uerId;
  public String password;
}
