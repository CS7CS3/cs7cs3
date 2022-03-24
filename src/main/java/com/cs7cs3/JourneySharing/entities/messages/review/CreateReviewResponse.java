package com.cs7cs3.JourneySharing.entities.messages.review;

import com.cs7cs3.JourneySharing.entities.UserReview;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class CreateReviewResponse extends Validatable {

    private CreateReviewResponse(UserReview userReview) {
        this.userReview = userReview;
    }

    public static CreateReviewResponse make(UserReview userReview) {
        return new CreateReviewResponse(userReview);
    }

    public UserReview userReview;
}
