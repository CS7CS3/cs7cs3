package com.cs7cs3.JourneySharing.entities.messages;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;



@Data
public class UpdateUserInfoRequest extends Validatable {

    public String username = "";
    public String avatarUrl = "";
    public String boi = "";

}
