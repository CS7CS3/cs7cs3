package com.cs7cs3.JourneySharing.entities.messages.user_info;

import java.util.List;

import com.cs7cs3.JourneySharing.entities.UserReview;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class GetReviewResponse extends Validatable {
    public static GetReviewResponse make(List<UserReview> reviews) {
        var res = new GetReviewResponse(reviews);
        return res;
    }

    private GetReviewResponse(List<UserReview> reviews) {
        this.reviews = reviews;
    }

    public List<UserReview> reviews;

}
