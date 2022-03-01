package com.cs7cs3.JourneySharing.entities.messages.review;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import lombok.Data;

@Data
public class GetReviewByUserIdRequest extends Validatable {
    public String userId = "";
}
