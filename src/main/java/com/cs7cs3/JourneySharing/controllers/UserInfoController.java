package com.cs7cs3.JourneySharing.controllers;

import java.util.Base64;

import javax.transaction.Transactional;

import com.cs7cs3.JourneySharing.db.UserInfoRepository;
import com.cs7cs3.JourneySharing.entities.UserInfo;
import com.cs7cs3.JourneySharing.entities.messages.Request;
import com.cs7cs3.JourneySharing.entities.messages.Response;
import com.cs7cs3.JourneySharing.entities.messages.UpdateUserInfoRequest;
import com.cs7cs3.JourneySharing.entities.messages.user_info.GetAvatarRequest;
import com.cs7cs3.JourneySharing.entities.messages.user_info.GetProfileRequest;
import com.cs7cs3.JourneySharing.entities.messages.user_info.GetProfileResponse;
import com.cs7cs3.JourneySharing.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostMapping("get-profile")
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

}
