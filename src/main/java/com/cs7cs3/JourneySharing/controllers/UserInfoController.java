package com.cs7cs3.JourneySharing.controllers;

import java.util.ArrayList;
import java.util.Base64;

import javax.transaction.Transactional;

import com.cs7cs3.JourneySharing.db.JourneyRepository;
import com.cs7cs3.JourneySharing.db.ReviewRepository;
import com.cs7cs3.JourneySharing.db.UserInfoRepository;
import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.UserInfo;
import com.cs7cs3.JourneySharing.entities.UserReview;
import com.cs7cs3.JourneySharing.entities.messages.Request;
import com.cs7cs3.JourneySharing.entities.messages.Response;
import com.cs7cs3.JourneySharing.entities.messages.UpdateUserInfoRequest;
import com.cs7cs3.JourneySharing.entities.messages.user_info.GetAvatarRequest;
import com.cs7cs3.JourneySharing.entities.messages.user_info.GetHistoryRequest;
import com.cs7cs3.JourneySharing.entities.messages.user_info.GetHistoryResponse;
import com.cs7cs3.JourneySharing.entities.messages.user_info.GetProfileRequest;
import com.cs7cs3.JourneySharing.entities.messages.user_info.GetProfileResponse;
import com.cs7cs3.JourneySharing.entities.messages.user_info.GetReviewRequest;
import com.cs7cs3.JourneySharing.entities.messages.user_info.GetReviewResponse;
import com.cs7cs3.JourneySharing.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user-info")
public class UserInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/get-profile")
    public Response<GetProfileResponse> getProfile(@RequestBody Request<GetProfileRequest> req) {
        var res = req.test();
        if (res.right.isPresent()) {
            return Response.makeError(res.right.get());
        }
        var payload = res.left;

        var userInfo = userInfoRepository.findById(payload.userId);
        if (userInfo.isEmpty()) {
            return Response.makeError("user does not exist");
        }

        return Response.make(Utils.nextToken(req.token), GetProfileResponse.make(userInfo.get()));
    }

    @PostMapping(value = "get-avatar", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getAvatar(@RequestBody Request<GetAvatarRequest> req) {
        var res = req.test();
        if (res.right.isPresent()) {
            return null;
        }
        var payload = res.left;

        var b64Avatar = userInfoRepository.getAvatar(payload.userId);

        return Base64.getDecoder().decode(b64Avatar);
    }

    @PostMapping("/update")
    @Transactional
    public Response<UserInfo> updateUserInfo(@RequestBody Request<UpdateUserInfoRequest> req) {
        logger.info(req.toString());

        var res = req.test();
        if (res.right.isPresent()) {
            return Response.makeError(res.right.get());
        }
        var payload = res.left;

        var un = payload.username;
        var ava = payload.avatar;
        var b = payload.boi;

        var userinfo = UserInfo.make(payload.id, un, ava, b);

        userInfoRepository.save(userinfo);

        return Response.make(Utils.nextToken(req.token), userinfo);
    }

    @PostMapping("/get-history")
    public Response<GetHistoryResponse> getHistory(@RequestBody Request<GetHistoryRequest> req) {
        var testRes = req.test();
        if (testRes.right.isPresent()) {
            return Response.makeError(testRes.right.get());
        }

        var payload = testRes.left;

        var uid = Utils.getIdByToken(req.token);

        var journeyIds = journeyRepository.findJourneyIdByUserId(uid, payload.from, payload.len);
        if (journeyIds == null || journeyIds.isEmpty()) {
            return Response.makeError("history does not exist");
        }

        var result = new ArrayList<Journey>();
        for (String jid : journeyIds) {
            var journey = journeyRepository.findById(jid);
            if (journey == null || journey.isEmpty()) {
                return Response.makeError("journey does not exist");
            }
            result.add(journey.get());
        }

        return Response.make(Utils.nextToken(req.token), GetHistoryResponse.make(result));
    }

    @PostMapping("/get-review")
    public Response<GetReviewResponse> getReview(@RequestBody Request<GetReviewRequest> req) {
        var testRes = req.test();
        if (testRes.right.isPresent()) {
            return Response.makeError(testRes.right.get());
        }
        var payload = testRes.left;
        var uid = Utils.getIdByToken(req.token);

        var ids = reviewRepository.findReviewIdByUserId(uid, payload.from, payload.len);
        if (ids == null || ids.isEmpty()) {
            return Response.makeError("reviews does not exist");
        }

        var result = new ArrayList<UserReview>();
        for (String rid : ids) {
            var review = reviewRepository.findById(rid);
            if (review == null || review.isEmpty()) {
                return Response.makeError("reviews does not exist");
            }

            // if(review.get().anonymous){
            // review.get().userId = "xxx"
            // }
            //
            result.add(review.get());
        }
        return Response.make(Utils.nextToken(req.token), GetReviewResponse.make(result));
    }

}
