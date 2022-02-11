package com.cs7cs3.JourneySharing.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
public class Journey extends Validatable {

  static public Journey make(@NonNull String userId, @NonNull Location from, @NonNull Location to) {
    var journey = new Journey();

    journey.hostId = userId;

    journey.journeyId = UUID.randomUUID().toString();
    journey.createdTime = System.currentTimeMillis() / 1000;
    journey.endTime = -1; // magic number

    journey.from = from;
    journey.to = to;

    return journey;
  }

  @Id
  public String journeyId = "";
  public long createdTime = 0;
  public long endTime = 0;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "from_latitude")),
      @AttributeOverride(name = "longitude", column = @Column(name = "from_longitude"))
  })
  public Location from = new Location();

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "to_latitude")),
      @AttributeOverride(name = "longitude", column = @Column(name = "to_longitude"))
  })
  public Location to = new Location();

  public String hostId = "";

  @ElementCollection
  public List<String> guestIds = new ArrayList<String>();

}
