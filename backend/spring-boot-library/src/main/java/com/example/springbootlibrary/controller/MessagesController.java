package com.example.springbootlibrary.controller;


import com.example.springbootlibrary.entity.Message;
import com.example.springbootlibrary.requestmodels.AdminQuestionRequest;
import com.example.springbootlibrary.service.MessagesService;
import com.example.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    private MessagesService messagesService;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);


    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value="Authorization") String token,
                            @RequestBody Message messageRequest) {
        long startTime = System.currentTimeMillis(); // record start time
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        messagesService.postMessage(messageRequest, userEmail);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time
        logger.info("API: /api/message/secure/add/message, Response state: OK, Request time: " + requestTime + "ms");

    }

    @PutMapping("/secure/admin/message")
    public void putMessage(@RequestHeader(value="Authorization") String token,
                           @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
        long startTime = System.currentTimeMillis(); // record start time
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only.");
        }
        messagesService.putMessage(adminQuestionRequest, userEmail);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time
        logger.info("API: /api/message/secure/admin/message, Response state: OK, Request time: " + requestTime + "ms");
    }

}









