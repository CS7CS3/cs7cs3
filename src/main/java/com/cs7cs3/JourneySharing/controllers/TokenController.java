package com.cs7cs3.JourneySharing.controllers;

import com.cs7cs3.JourneySharing.db.UserInfoRepository;
import com.cs7cs3.JourneySharing.entities.messages.Request;
import com.cs7cs3.JourneySharing.entities.messages.Response;
import com.cs7cs3.JourneySharing.entities.messages.token.CheckTokenRequest;
import com.cs7cs3.JourneySharing.entities.messages.token.GetInfoByTokenRequest;
import com.cs7cs3.JourneySharing.entities.messages.token.GetInfoByTokenResponse;
import com.cs7cs3.JourneySharing.entities.messages.token.RefreshTokenRequest;
import com.cs7cs3.JourneySharing.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/tokens")
public class TokenController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostMapping
    public boolean checkToken(@RequestBody Request<CheckTokenRequest> req) {
        var token = req.token;
        // token:
        // btV5NwilFF9Ky67YjJxdxLH0JjOKks7pjyf0u8ctpcgWo+gP1L1m4p99n0J62HdV8kTPzXaPcQY2/sk7lvF3/15kwuXNQGT19IYlRttsS4E=
        var expire = Utils.getTimeByToken(token);
        var time = System.currentTimeMillis() / 1000L;
        return (expire - time > 0);
    }

    @PostMapping("/refresh")
    public String refreshToken(@RequestBody Request<RefreshTokenRequest> req) {
        var token = req.token;
        var new_token = Utils.makeToken(Utils.getIdByToken(token));
        return new_token;
    }

    @PostMapping("/info")
    public Response<GetInfoByTokenResponse> getInfo(@RequestBody Request<GetInfoByTokenRequest> req) {
        var id = Utils.getIdByToken(req.token);
        if (id.isEmpty()) {
            return Response.makeError("token does not exist");
        }

        var userInfo = userInfoRepository.findById(id);
        return Response.make(Utils.nextToken(req.token), GetInfoByTokenResponse.make(userInfo.get()));

    }
}
