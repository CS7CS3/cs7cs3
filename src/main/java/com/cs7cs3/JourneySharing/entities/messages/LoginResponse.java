package com.cs7cs3.JourneySharing.entities.messages;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import lombok.Data;

@Data
public class LoginResponse extends Validatable {
    public static LoginResponse make(){
        return null;
    }
    private LoginResponse(){

    }

}
