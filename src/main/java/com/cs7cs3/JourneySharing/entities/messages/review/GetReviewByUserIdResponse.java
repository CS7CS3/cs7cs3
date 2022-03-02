package com.cs7cs3.JourneySharing.entities.messages.review;

import com.cs7cs3.JourneySharing.entities.UserReview;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class GetReviewByUserIdResponse extends Validatable {
    public static GetReviewByUserIdResponse make(UserReview userReview) {
        return new GetReviewByUserIdResponse(userReview);
    }

    private GetReviewByUserIdResponse(UserReview review) {
        this.userReview = review;
    }

    public UserReview userReview;
}
