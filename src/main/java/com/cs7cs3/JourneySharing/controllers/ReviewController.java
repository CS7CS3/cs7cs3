package com.cs7cs3.JourneySharing.controllers;

import com.cs7cs3.JourneySharing.db.ReviewRepository;
import com.cs7cs3.JourneySharing.entities.UserReview;
import com.cs7cs3.JourneySharing.entities.request.CreateReviewRequest;
import com.cs7cs3.JourneySharing.entities.request.Request;
import com.cs7cs3.JourneySharing.entities.response.Response;
import com.cs7cs3.JourneySharing.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/review")
public class ReviewController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ReviewRepository repository;

  @GetMapping("/{id}")
  public Response<UserReview> get(@PathVariable("id") String id, @RequestParam("token") String token) {
    if (!Utils.validateToken(token)) {
      return Response.makeError("token validation failed");
    }

    var res = repository.findById(id);
    if (!res.isPresent()) {
      return Response.makeError("review does not exist");
    }

    return Response.makeResponse(Utils.nextToken(token), res.get());
  }

  @PostMapping("/create")
  @Transactional
  public Response<UserReview> createReview(@RequestBody Request<CreateReviewRequest> req) {
    logger.info(req.toString());

    if (!req.validate()) {
      return Response.makeError("request validation failed");
    }

    if (!Utils.validateToken(req.token)) {
      logger.error("?");
      return Response.makeError("token validation failed");
    }

    if (!req.payload.isPresent()) {
      return Response.makeError("payload does not exist");
    }

    var payload = req.payload.get();

    if (!payload.validate()) {
      return Response.makeError("payload validation failed");
    }

    var review = UserReview.make( payload.userId,payload.revieweeId, payload.rating, payload.content);
    System.out.println(review);
    repository.save(review);
//    repository.insert(payload.reviewId,payload.userId, payload.rating, payload.content,payload.anonymous, payload.getRevieweeId());

    return Response.makeResponse(Utils.nextToken(req.token), review);
  }

}
