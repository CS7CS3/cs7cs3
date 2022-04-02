package com.cs7cs3.JourneySharing.controllers;

import com.cs7cs3.JourneySharing.db.UserInfoRepository;
import com.cs7cs3.JourneySharing.entities.messages.Request;
import com.cs7cs3.JourneySharing.entities.messages.Response;
import com.cs7cs3.JourneySharing.entities.messages.token.CheckTokenRequest;
import com.cs7cs3.JourneySharing.entities.messages.token.CheckTokenResponse;
import com.cs7cs3.JourneySharing.entities.messages.token.GetInfoByTokenRequest;
import com.cs7cs3.JourneySharing.entities.messages.token.GetInfoByTokenResponse;
import com.cs7cs3.JourneySharing.entities.messages.token.RefreshTokenRequest;
import com.cs7cs3.JourneySharing.entities.messages.token.RefreshTokenResponse;
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
    public Response<CheckTokenResponse> checkToken(@RequestBody Request<CheckTokenRequest> req) {
        var token = req.token;
        var expire = Utils.getTimeByToken(token);
        var time = Utils.timestamp();
        return Response.make(Utils.nextToken(token), CheckTokenResponse.make(expire > time));
    }

    @PostMapping("/refresh")
    public Response<RefreshTokenResponse> refreshToken(@RequestBody Request<RefreshTokenRequest> req) {
        var token = req.token;
        return Response.make(Utils.nextToken(token));
    }

    @PostMapping("/info")
    public Response<GetInfoByTokenResponse> getInfo(@RequestBody Request<GetInfoByTokenRequest> req) {
        var id = Utils.getIdByToken(req.token);
        if (id.isEmpty()) {
            return Response.makeError("token does not exist");
        }

        var userInfo = userInfoRepository.findById(id);
        if (userInfo.isEmpty()) {
            return Response.makeError("user info does not exist");
        }

        return Response.make(Utils.nextToken(req.token), GetInfoByTokenResponse.make(userInfo.get()));

    }
}
