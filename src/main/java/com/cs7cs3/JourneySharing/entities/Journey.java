package com.cs7cs3.JourneySharing.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "journey")
public class Journey extends Validatable {

  public static Journey make(@NonNull String userId, @NonNull Location from, @NonNull Location to) {
    var journey = new Journey();

    journey.id = UUID.randomUUID().toString();

    journey.createdTime = System.currentTimeMillis() / 1000;

    journey.from = from;
    journey.to = to;
    journey.members.add(JourneyMember.make(userId, UserStatus.Waiting));

    journey.host = userId;

    return journey;
  }

  public static Journey make(@NonNull CreateJourneyRequest req) {
    var journey = new Journey();

    journey.id = UUID.randomUUID().toString();

    journey.createdTime = System.currentTimeMillis() / 1000;

    journey.from = req.from;
    journey.to = req.to;
    // journey.members.add(req.userId);
    journey.members.add(JourneyMember.make(req.userId, UserStatus.Waiting));
    journey.host = req.userId;

    return journey;
  }

  // 不该是静态吧
  public enum JourneyStatus {
    Waiting, Travelling, End;

    private static JourneyStatus[] values = null;

    public static JourneyStatus cast(int i) {
      if (values == null) {
        values = JourneyStatus.values();
      }
      return values[i];
    }
  }

  // 不知道表示了什么，不该出现在这里
  public enum UserStatus {
    PendingApproval, Waiting, Travelling, Arrived;

    private static UserStatus[] values = null;

    public static UserStatus cast(int i) {
      if (values == null) {
        values = UserStatus.values();
      }
      return values[i];
    }
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
  // @Column(name = "user_id")
  // public List<String> members = new ArrayList<String>();
  // @Embedded
  public List<JourneyMember> members = new ArrayList<JourneyMember>();

}
