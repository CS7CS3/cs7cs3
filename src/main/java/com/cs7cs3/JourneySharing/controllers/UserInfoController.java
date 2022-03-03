package com.cs7cs3.JourneySharing.controllers;

import javax.transaction.Transactional;

import com.cs7cs3.JourneySharing.db.UserInfoRepository;
import com.cs7cs3.JourneySharing.entities.UserInfo;
import com.cs7cs3.JourneySharing.entities.messages.Request;
import com.cs7cs3.JourneySharing.entities.messages.Response;
import com.cs7cs3.JourneySharing.entities.messages.UpdateUserInfoRequest;
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

@RestController
@RequestMapping("/user_info")
public class UserInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoRepository repository;

    @GetMapping("/{id}")
    public Response<UserInfo> get(@PathVariable("id") String id, @RequestParam("token") String token) {

        if (!Utils.validateToken(token)) {
            return Response.makeError("token validation failed");
        }

        var res = repository.findById(id);
        System.out.println(res);

        if (!res.isPresent()) {
            return Response.makeError("user does not exist");
        }

        return Response.make(Utils.nextToken(token), res.get());
    }

    @GetMapping("/{id}/ava")
    public Response<String> getAva(@PathVariable("id") String id, @RequestParam("token") String token) {

        if (!Utils.validateToken(token)) {
            return Response.makeError("token validation failed");
        }

        var res = repository.findById(id);

        System.out.println(res);

        if (!res.isPresent()) {
            return Response.makeError("user does not exist");
        }

        else {

            // base64 validation

            var ava = res.get().avatarUrl;
            return Response.make(Utils.nextToken(token), ava);
        }

    }

    @PostMapping("/update/{id}")
    @Transactional
    public Response<UserInfo> updateUserInfo(@PathVariable String id, @RequestBody Request<UpdateUserInfoRequest> req) {
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

        var un = payload.username;
        var ava = payload.avatarUrl;
        var b = payload.boi;
        var userinfo = UserInfo.make(id, un, ava, b);

        System.out.println(userinfo);
        repository.save(userinfo);

        return Response.make(Utils.nextToken(req.token), userinfo);

    }

}
