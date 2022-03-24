package com.cs7cs3.JourneySharing.controllers;

import javax.transaction.Transactional;

import com.cs7cs3.JourneySharing.db.ReviewRepository;
import com.cs7cs3.JourneySharing.db.UserInfoRepository;
import com.cs7cs3.JourneySharing.entities.UserInfo;
import com.cs7cs3.JourneySharing.entities.UserReview;
import com.cs7cs3.JourneySharing.entities.messages.Request;
import com.cs7cs3.JourneySharing.entities.messages.Response;
import com.cs7cs3.JourneySharing.entities.messages.review.CreateReviewRequest;
import com.cs7cs3.JourneySharing.entities.messages.review.CreateReviewResponse;
import com.cs7cs3.JourneySharing.entities.messages.review.GetReviewByUserIdRequest;
import com.cs7cs3.JourneySharing.entities.messages.review.GetReviewByUserIdResponse;
import com.cs7cs3.JourneySharing.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ReviewRepository reviewRepository;
  @Autowired
  private UserInfoRepository userInfoRepository;

  @PostMapping("/get-by-userid")
  public Response<GetReviewByUserIdResponse> get(@RequestBody Request<GetReviewByUserIdRequest> req) {
    var testRes = req.test();
    if (testRes.right.isPresent()) {
      return Response.makeError(testRes.right.get());
    }
    var payload = testRes.left;

    var res = reviewRepository.findByUser(payload.userId, payload.from, payload.len);
    if (res == null) {
      return Response.makeError("review does not exist");
    }

    return Response.make(Utils.nextToken(req.token), GetReviewByUserIdResponse.make(res));
  }

  @PostMapping("/create")
  @Transactional
  public Response<CreateReviewResponse> createReview(@RequestBody Request<CreateReviewRequest> req) {
    logger.info(req.toString());

    var res = req.test();
    if (res.right.isPresent()) {
      return Response.makeError(res.right.get());
    }
    var payload = res.left;

    var review = UserReview.make(payload.userId, payload.revieweeId,payload.rating, payload.content);
    logger.info(review.toString());
    reviewRepository.save(review);

    var u = userInfoRepository.findById(payload.userId);

    var c = u.get().counter + 1; // counter + 1
    var o_r = u.get().rating; // old rating
    var n_r = (u.get().counter * o_r +payload.rating) / c; // calculate the new rating

    var userInfo = UserInfo.makeRating(payload.userId,u.get().username,u.get().avatar,u.get().bio,c,n_r);
    userInfoRepository.save(userInfo);

    var review_id = review.id;
    System.out.println(review_id);
    System.out.println(payload.revieweeId);

    userInfoRepository.addReview(payload.revieweeId,review_id);

    return Response.make(Utils.nextToken(req.token), CreateReviewResponse.make(review));
  }

}
