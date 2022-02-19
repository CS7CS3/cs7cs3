package com.cs7cs3.JourneySharing.controllers;

import javax.transaction.Transactional;

import com.cs7cs3.JourneySharing.db.AccountRepository;
import com.cs7cs3.JourneySharing.db.UserInfoRepository;
import com.cs7cs3.JourneySharing.entities.Account;
import com.cs7cs3.JourneySharing.entities.UserInfo;
import com.cs7cs3.JourneySharing.entities.base.Empty;
import com.cs7cs3.JourneySharing.entities.request.RegisterRequest;
import com.cs7cs3.JourneySharing.entities.response.Response;
import com.cs7cs3.JourneySharing.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private UserInfoRepository userInfoRepository;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Transactional
  private boolean createAccount(RegisterRequest req) {
    try {
      Account account = Account.make(req);
      UserInfo userInfo = UserInfo.make(account, req);

      accountRepository.save(account);
      userInfoRepository.save(userInfo);
    } catch (Exception e) {
      logger.error(e.toString());
      return false;
    }

    return true;
  }

  @PostMapping
  public Response<Empty> register(@RequestBody RegisterRequest req) {
    logger.info(req.toString());

    if (!req.validate()) {
      return Response.makeError("request validation failed");
    }

    if (!createAccount(req)) {
      return Response.makeError("duplicated account");
    }

    return Response.makeResponse(Utils.makeToken(req.username));
  }

}
