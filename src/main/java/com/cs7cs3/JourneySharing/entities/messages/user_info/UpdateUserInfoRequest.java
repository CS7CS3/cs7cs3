package com.cs7cs3.JourneySharing.entities.messages.user_info;

import com.cs7cs3.JourneySharing.entities.Gender;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class UpdateUserInfoRequest extends Validatable {
    public String username;
    public String avatar;
    public String bio;
    public Gender gender;
}
