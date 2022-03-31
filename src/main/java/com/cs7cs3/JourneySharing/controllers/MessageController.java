package com.cs7cs3.JourneySharing.controllers;

import com.cs7cs3.JourneySharing.db.AccountRepository;
import com.cs7cs3.JourneySharing.db.MessageRepository;
import com.cs7cs3.JourneySharing.entities.Message;
import com.cs7cs3.JourneySharing.entities.messages.Request;
import com.cs7cs3.JourneySharing.entities.messages.Response;
import com.cs7cs3.JourneySharing.entities.messages.message.GetMessageRequest;
import com.cs7cs3.JourneySharing.entities.messages.message.GetMessageResponse;
import com.cs7cs3.JourneySharing.entities.messages.message.GetPublicKeyRequest;
import com.cs7cs3.JourneySharing.entities.messages.message.GetPublicKeyResponse;
import com.cs7cs3.JourneySharing.entities.messages.message.SendMessageRequest;
import com.cs7cs3.JourneySharing.entities.messages.message.SendMessageResponse;
import com.cs7cs3.JourneySharing.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private AccountRepository accountRepository;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @PostMapping("/send")
  public Response<SendMessageResponse> send(@RequestBody Request<SendMessageRequest> req) {
    logger.info(req.toString());

    var res = req.test();
    if (res.right.isPresent()) {
      return Response.makeError(res.right.get());
    }
    var payload = res.left;
    var uid = Utils.getIdByToken(req.token);

    var message = Message.make(uid, payload);
    try {
      messageRepository.save(message);
    } catch (DataIntegrityViolationException e) {
      return Response.makeError("user id does not exist");
    }

    return Response.make(Utils.nextToken(req.token), SendMessageResponse.make());
  }

  @PostMapping("/get")
  public Response<GetMessageResponse> get(@RequestBody Request<GetMessageRequest> req) {
    logger.info(req.toString());

    var res = req.test();
    if (res.right.isPresent()) {
      return Response.makeError(res.right.get());
    }
    var payload = res.left;
    var uid = Utils.getIdByToken(req.token);

    var messages = messageRepository.getMessagesByUserIdOrderByTimestamp(uid, payload.from, payload.len);
    if (messages == null) {
      return Response.make("error to get messages");
    }

    return Response.make(Utils.nextToken(req.token), GetMessageResponse.make(messages));
  }

  @PostMapping("get-public-key")
  public Response<GetPublicKeyResponse> getPublicKey(@RequestBody Request<GetPublicKeyRequest> req) {
    logger.info(req.toString());

    var res = req.test();
    if (res.right.isPresent()) {
      return Response.makeError(res.right.get());
    }
    var payload = res.left;

    var publicKey = accountRepository.getPublicKey(payload.userId);

    return Response.make(Utils.nextToken(req.token), GetPublicKeyResponse.make(publicKey));
  }
}
