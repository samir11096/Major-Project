package com.example.springbootlibrary.controller;

import com.example.springbootlibrary.requestmodels.ReviewRequest;
import com.example.springbootlibrary.service.ReviewService;
import com.example.springbootlibrary.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);



    public ReviewController(ReviewService reviewService){
        this.reviewService=reviewService;
    }

    @PostMapping("/secure")
    public void postReview(@RequestHeader(value="Authorization") String token,
                           @RequestBody ReviewRequest reviewRequest) throws  Exception{
        long startTime = System.currentTimeMillis(); // record start time
        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if(userEmail==null){
            throw new Exception("User email is missing");
        }
        reviewService.postReview(userEmail,reviewRequest);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time
        logger.info("API: /api/reviews/secure, Response state: OK, Request time: " + requestTime + "ms");

    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value="Authorization") String token,
                                    @RequestParam Long bookId) throws Exception {
        long startTime = System.currentTimeMillis(); // record start time
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        if (userEmail == null) {
            throw new Exception("User email is missing");
        }
        Boolean isReviewd =  reviewService.userReviewListed(userEmail, bookId);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time
        if (isReviewd != null) {
            logger.info("API: /api/reviews/secure/user/book, Response state: OK, Request time: " + requestTime + "ms");
        } else {
            logger.info("API: /api/review/secure/user/book, Response state: ERROR, Request time: " + requestTime + "ms");
        }
        return isReviewd;

    }
}
