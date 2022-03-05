package com.cs7cs3.JourneySharing.entities.messages.journey;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import lombok.Data;

@Data
public class GetHistoryRequest extends Validatable {
    public String userId = "";
    public int from;
    public int len;
}
