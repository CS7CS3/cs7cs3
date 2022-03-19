package com.cs7cs3.JourneySharing.entities.messages.user_info;

import com.cs7cs3.JourneySharing.entities.UserInfo;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

public class GetProfileResponse extends Validatable {
  public static GetProfileResponse make(UserInfo userInfo) {
    return new GetProfileResponse(userInfo);
  }

  private GetProfileResponse(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  UserInfo userInfo;
}
