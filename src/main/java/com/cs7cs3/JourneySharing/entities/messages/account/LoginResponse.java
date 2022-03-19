package com.cs7cs3.JourneySharing.entities.messages.account;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class LoginResponse extends Validatable {
    public static LoginResponse make(String userId) {
        return new LoginResponse(userId);
    }

    private LoginResponse(String userId) {
        this.userId = userId;
    }

    public String userId;

}
