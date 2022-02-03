package com.cs7cs3.JourneySharing.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
@Entity
public class Journey extends Validatable {
  @Id
  public String journeyId = "";
  public long createdTime = 0;
  public long endTime = 0;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "from_latitude")),
      @AttributeOverride(name = "longitude", column = @Column(name = "from_longitude"))
  })
  public Destination from = new Destination();

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "to_latitude")),
      @AttributeOverride(name = "longitude", column = @Column(name = "to_longitude"))
  })
  public Destination to = new Destination();

  public String hostId = "";

  @ElementCollection
  public List<String> guestIds = new ArrayList<String>();

}
