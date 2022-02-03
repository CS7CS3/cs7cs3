package com.cs7cs3.JourneySharing.entities;

import java.util.ArrayList;
import java.util.List;

import com.cs7cs3.JourneySharing.entities.base.JourneyId;
import com.cs7cs3.JourneySharing.entities.base.Timestamp;
import com.cs7cs3.JourneySharing.entities.base.UserId;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class Journey extends Validatable {
  public JourneyId journeyId = new JourneyId();
  public Timestamp createdTime = new Timestamp();
  public Timestamp endTime = new Timestamp();
  public Destination from = new Destination();
  public Destination to = new Destination();
  public UserId hostId = new UserId();
  public List<UserId> guestIds = new ArrayList<UserId>();

  public String getJourneyId() {
    return journeyId.value;
  }

  public void setJourneyId(String value) {
    journeyId.value = value;
  }

  public long getCreatedTime() {
    return createdTime.value;
  }

  public void setCreatedTime(long value) {
    createdTime.value = value;
  }

  public long getEndTime() {
    return endTime.value;
  }

  public void setEndTime(long value) {
    endTime.value = value;
  }

  public String getHostId() {
    return hostId.value;
  }

  public void setHostId(String value) {
    hostId.value = value;
  }

  public List<String> getGuestIds() {
    ArrayList<String> list = new ArrayList<String>();
    for (UserId userId : guestIds) {
      list.add(userId.value);
    }

    return list;
  }

  public void setHostId(List<String> value) {
    guestIds.clear();
    for (String string : value) {
      UserId userId = new UserId();
      userId.value = string;
      guestIds.add(userId);
    }
  }

  // @Override
  public boolean isEmpty() {
    return true;
  }

}
