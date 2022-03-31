package com.cs7cs3.JourneySharing.entities.messages.account;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class LoginResponse extends Validatable {
    public static LoginResponse make(String userId, String privateKey) {
        return new LoginResponse(userId, privateKey);
    }

    private LoginResponse(String userId, String privateKey) {
        this.userId = userId;
        this.privateKey = privateKey;
    }

    public String userId;
    public String privateKey;
}
