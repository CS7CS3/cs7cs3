package com.cs7cs3.JourneySharing.controllers;// package com.cs7cs3.JourneySharing.controllers;

// import java.util.Optional;

// import com.cs7cs3.JourneySharing.db.UserInfoRepository;
// import com.cs7cs3.JourneySharing.entities.Response;
// import com.cs7cs3.JourneySharing.entities.User;
// import com.cs7cs3.JourneySharing.entities.base.Empty;
// import com.cs7cs3.JourneySharing.entities.request.Request;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/user_info")
// public class UserInfoController {
//   private Logger logger = LoggerFactory.getLogger(this.getClass());

//   @Autowired
//   private UserInfoRepository repository;

//   @GetMapping("/{id}")
//   public Response<User> get( Request<Empty> req, @PathVariable("id") String id) {
//     logger.info(req.toString());
//     if (!req.validate()) {
//       logger.error("?");
//       return Response.makeResponse(false, "?", "", Optional.empty());
//     }

//     if (/* var token = req.token; validate(token) */ false) {
//       logger.error("?");
//       return Response.makeResponse(false, "?", "", Optional.empty());
//     }

//     var res = repository.findById(id);
//     if (!res.isPresent()) {
//       return Response.makeResponse(false, "id does not exist", /* next_token(token) */ "", Optional.empty());
//     }

//     return Response.makeResponse(/* next_token(token) */ "", res.get());
//   }

//   @PostMapping
//   public Response<Empty> post(@RequestBody Request<User> req) {
//     logger.info(req.toString());
//     if (!req.validate()) {
//       logger.error("?");
//       return Response.makeResponse(false, "?", "", Optional.empty());
//     }

//     // empty payload, early reject
//     if (!req.payload.isPresent()) {
//       logger.error("?");
//       return Response.makeResponse(false, "?", "", Optional.empty());
//     }

//     if (/* var token = req.token; validate(token) */ false) {
//       logger.error("?");
//       return Response.makeResponse(false, "?", "", Optional.empty());
//     }

//     // schema validation
//     if (!req.payload.get().validate()) {
//       logger.error("?");
//       return Response.makeResponse(false, "?", "", Optional.empty());
//     }

//     repository.save(req.payload.get());
//     return Response.makeResponse(true, "", /* next_token(token) */ "", Optional.empty());
//   }

//   @PutMapping
//   public Response<Empty> put(@RequestBody Request<User> req) {
//     logger.info(req.toString());
//     if (!req.validate()) {
//       logger.error("?");
//       return Response.makeResponse(false, "?", "", Optional.empty());
//     }

//     // empty payload, early reject
//     if (!req.payload.isPresent()) {
//       logger.error("?");
//       return Response.makeResponse(false, "?", "", Optional.empty());
//     }

//     if (/* var token = req.token; validate(token) */ false) {
//       logger.error("?");
//       return Response.makeResponse(false, "?", "", Optional.empty());
//     }

//     // schema validation
//     if (!req.payload.get().validate()) {
//       logger.error("?");
//       return Response.makeResponse(false, "?", "", Optional.empty());
//     }

//     repository.save(req.payload.get());
//     return Response.makeResponse(true, "", /* next_token(token) */ "", Optional.empty());
//   }


// }


import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController extends Validatable {
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());


}
