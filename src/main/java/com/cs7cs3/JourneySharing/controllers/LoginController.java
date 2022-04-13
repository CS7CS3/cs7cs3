package com.cs7cs3.JourneySharing.controllers;

import com.cs7cs3.JourneySharing.db.AccountRepository;
import com.cs7cs3.JourneySharing.db.UserInfoRepository;
import com.cs7cs3.JourneySharing.entities.messages.Request;
import com.cs7cs3.JourneySharing.entities.messages.Response;
import com.cs7cs3.JourneySharing.entities.messages.account.ChangePasswordRequest;
import com.cs7cs3.JourneySharing.entities.messages.account.ChangePasswordResponse;
import com.cs7cs3.JourneySharing.entities.messages.account.LoginRequest;
import com.cs7cs3.JourneySharing.entities.messages.account.LoginResponse;
import com.cs7cs3.JourneySharing.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private UserInfoRepository userInfoRepository;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @ResponseBody
  @RequestMapping("/login")
  public Response<LoginResponse> login(@RequestBody LoginRequest req) {
    logger.info(req.toString());
    if (!req.validate()) {
      return Response.makeError("request validation failed");
    }

    boolean ret = accountRepository.testPassword(req.username, req.password) > 0;
    if (!ret) {
      return Response.makeError("wrong username or password");
    }

    var id = userInfoRepository.getIdByUsername(req.username);
    var privateKey = accountRepository.getPrivateKey(id);

    return Response.make(Utils.makeToken(id), LoginResponse.make(id, privateKey));
  }

  @ResponseBody
  @RequestMapping("/change-password")
  public Response<ChangePasswordResponse> changePassword(@RequestBody Request<ChangePasswordRequest> req) {
    logger.info(req.toString());

    var res = req.test();
    if (res.right.isPresent()) {
      return Response.makeError(res.right.get());
    }
    var payload = res.left;

    boolean ret = accountRepository.testPassword(payload.username, payload.oldPassword) > 0;
    if (!ret) {
      return Response.makeError("wrong username or password");
    }

    var accountOpt = accountRepository.findById(Utils.getIdByToken(req.token));
    if (accountOpt.isEmpty()) {
      return Response.makeError("invalid account");
    }
    var account = accountOpt.get();

    account.password = payload.newPassword;
    accountRepository.save(account);

    return Response.make(Utils.nextToken(req.token), ChangePasswordResponse.make());
  }

}
