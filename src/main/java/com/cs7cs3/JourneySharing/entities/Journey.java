package com.cs7cs3.JourneySharing.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.cs7cs3.JourneySharing.entities.messages.journey.CreateJourneyRequest;
import com.cs7cs3.JourneySharing.utils.Utils;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "journey")
public class Journey extends Validatable {

  public static Journey makeFake(String userId, Location from, Location to) {
    var journey = new Journey();
    var rand = new Random();

    journey.id = Utils.makeUUID();

    journey.createdTime = Utils.timestamp();
    journey.status = JourneyStatus.Waiting;
    journey.endTime = -1;

    from.addLatitude(rand.nextDouble(0, 100));
    from.addLogitude(rand.nextDouble(0, 100));
    to.addLatitude(rand.nextDouble(0, 100));
    to.addLogitude(rand.nextDouble(0, 100));
    journey.from = from;
    journey.to = to;
    journey.members.add(JourneyMember.make(userId, UserStatus.Waiting));

    journey.host = userId;

    return journey;
  }

  public static Journey make(@NonNull String userId, @NonNull Location from, @NonNull Location to) {
    var journey = new Journey();

    journey.id = Utils.makeUUID();

    journey.createdTime = Utils.timestamp();

    journey.from = from;
    journey.to = to;
    journey.members.add(JourneyMember.make(userId, UserStatus.Waiting));

    journey.host = userId;

    return journey;
  }

  public static Journey make(@NonNull String userId, @NonNull CreateJourneyRequest req) {
    var journey = new Journey();

    journey.id = Utils.makeUUID();

    journey.createdTime = Utils.timestamp();

    journey.from = req.from;
    journey.to = req.to;
    journey.members.add(JourneyMember.make(userId, UserStatus.Waiting));
    journey.host = userId;

    return journey;
  }

  @Embeddable
  public static class JourneyMember {
    public static JourneyMember make(String userId, UserStatus status) {
      var journeyMember = new JourneyMember();
      journeyMember.userId = userId;
      journeyMember.status = status;

      return journeyMember;
    }

    public String userId;
    public UserStatus status = UserStatus.Waiting;
  }

  @Id
  public String id;
  public long createdTime = 0;
  public long endTime = 0;
  public JourneyStatus status = JourneyStatus.Waiting;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "from_latitude", columnDefinition = "DOUBLE DEFAULT 0.0", nullable = false)),
      @AttributeOverride(name = "longitude", column = @Column(name = "from_longitude", columnDefinition = "DOUBLE DEFAULT 0.0", nullable = false))
  })
  public Location from = new Location();

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "to_latitude", columnDefinition = "DOUBLE DEFAULT 0.0", nullable = false)),
      @AttributeOverride(name = "longitude", column = @Column(name = "to_longitude", columnDefinition = "DOUBLE DEFAULT 0.0", nullable = false))
  })
  public Location to = new Location();

  public int maxMember = 1;
  public String host = "";

  @ElementCollection
  public List<JourneyMember> members = new ArrayList<JourneyMember>();

  @Override
  public boolean validate() {
    return id != "";
  }
}
