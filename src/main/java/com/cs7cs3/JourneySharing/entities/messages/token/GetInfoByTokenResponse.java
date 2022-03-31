package com.cs7cs3.JourneySharing.entities.messages.token;

import com.cs7cs3.JourneySharing.entities.UserInfo;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class GetInfoByTokenResponse extends Validatable {
    public static GetInfoByTokenResponse make(UserInfo userInfo) {
        return new GetInfoByTokenResponse(userInfo);
    }

    private GetInfoByTokenResponse(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    UserInfo userInfo;

}
