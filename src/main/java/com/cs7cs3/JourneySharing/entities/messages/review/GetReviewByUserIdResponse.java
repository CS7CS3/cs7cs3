package com.cs7cs3.JourneySharing.entities.messages.review;

import java.util.List;

import com.cs7cs3.JourneySharing.entities.UserReview;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class GetReviewByUserIdResponse extends Validatable {
    public static GetReviewByUserIdResponse make(List<UserReview> userReviews) {
        var resp = new GetReviewByUserIdResponse(userReviews);
        for (UserReview review : resp.userReviews) {
            if (review.anonymous) {
                review.userId = "*";
            }
        }
        return resp;
    }

    private GetReviewByUserIdResponse(List<UserReview> userReviews) {
        this.userReviews = userReviews;
    }

    public List<UserReview> userReviews;
}
