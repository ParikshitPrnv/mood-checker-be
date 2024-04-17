package org.logger.logger.controllers;

import org.logger.logger.dto.RegisterMoodRequest;
import org.logger.logger.dto.RegisterMoodResponse;
import org.logger.logger.dto.Emp;
import org.logger.logger.loggingFramework.LogStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SayHello {

    @GetMapping("hello")
    public ResponseEntity<String> sayHello() {

        Emp emp = new Emp("Pranav", "Gautam", "pranav@user.com");
//		logStructure.logInfo("INCOMING_API", emp);
        LogStructure.logInfo("EMP_DETAILS", emp);
        Daddy daddy = new Daddy();
        daddy.whoSYourDaddy();
        return new ResponseEntity<String>("hi1", HttpStatus.OK);
    }

    @PostMapping("mood")
    public  ResponseEntity<RegisterMoodResponse> registerMood(@RequestBody RegisterMoodRequest req){
        RegisterMoodResponse resp = new RegisterMoodResponse(req.getUserId(),req.getMood());
        LogStructure.logInfo("INCOMING_API", req);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
