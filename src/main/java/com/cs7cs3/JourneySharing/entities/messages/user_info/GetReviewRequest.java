package com.cs7cs3.JourneySharing.entities.messages.user_info;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import lombok.Data;


@Data
public class GetReviewRequest extends Validatable {
    public int from;
    public int len;
}