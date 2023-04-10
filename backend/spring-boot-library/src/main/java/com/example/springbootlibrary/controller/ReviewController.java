package com.example.springbootlibrary.controller;

import com.example.springbootlibrary.requestmodels.ReviewRequest;
import com.example.springbootlibrary.service.ReviewService;
import com.example.springbootlibrary.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService=reviewService;
    }

    @PostMapping("/secure")
    public void postReview(@RequestHeader(value="Authorization") String token,
                           @RequestBody ReviewRequest reviewRequest) throws  Exception{
        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if(userEmail==null){
            throw new Exception("User email is missing");
        }

        reviewService.postReview(userEmail,reviewRequest);
    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value="Authorization") String token,
                                    @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        if (userEmail == null) {
            throw new Exception("User email is missing");
        }
        return reviewService.userReviewListed(userEmail, bookId);
    }
}
