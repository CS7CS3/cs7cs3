package com.cs7cs3.JourneySharing.entities.messages.journey;

import java.util.List;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class GetUnapprovedUsersResponse extends Validatable {

  public static GetUnapprovedUsersResponse make(List<String> userIds) {
    return new GetUnapprovedUsersResponse(userIds);
  }

  private GetUnapprovedUsersResponse(List<String> userIds) {
    this.userIds = userIds;
  }

  List<String> userIds;
}
