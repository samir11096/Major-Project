package com.example.springbootlibrary.controller;

import com.example.springbootlibrary.requestmodels.AddBookRequest;
import com.example.springbootlibrary.service.AdminService;
import com.example.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);


    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("/secure/increase/book/quantity")
    public void increaseBookQuantity(@RequestHeader(value="Authorization") String token,
                                     @RequestParam Long bookId) throws Exception {
//        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
//        if (admin == null || !admin.equals("admin")) {
//            throw new Exception("Administration page only");
//        }
//        adminService.increaseBookQuantity(bookId);

        long startTime = System.currentTimeMillis(); // record start time
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.increaseBookQuantity(bookId);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time
        logger.info("API: /api/admin/secure/increase/book/quantity, Response state: OK, Request time: " + requestTime + "ms");
    }

    @PutMapping("/secure/decrease/book/quantity")
    public void decreaseBookQuantity(@RequestHeader(value="Authorization") String token,
                                     @RequestParam Long bookId) throws Exception {
//        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
//        if (admin == null || !admin.equals("admin")) {
//            throw new Exception("Administration page only");
//        }
//        adminService.decreaseBookQuantity(bookId);
        long startTime = System.currentTimeMillis(); // record start time
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.increaseBookQuantity(bookId);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time
        logger.info("API: /api/admin/secure/decrease/book/quantity, Response state: OK, Request time: " + requestTime + "ms");
    }

    @PostMapping("/secure/add/book")
    public void postBook(@RequestHeader(value="Authorization") String token,
                         @RequestBody AddBookRequest addBookRequest) throws Exception {
//        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
//        if (admin == null || !admin.equals("admin")) {
//            throw new Exception("Administration page only");
//        }
//        adminService.postBook(addBookRequest);
        long startTime = System.currentTimeMillis(); // record start time
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.postBook(addBookRequest);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time
        logger.info("API: /api/admin/secure/add/book, Response state: OK, Request time: " + requestTime + "ms");
    }

    @DeleteMapping("/secure/delete/book")
    public void deleteBook(@RequestHeader(value="Authorization") String token,
                           @RequestParam Long bookId) throws Exception {
        long startTime = System.currentTimeMillis(); // record start time
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.deleteBook(bookId);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time
        logger.info("API: /api/admin/secure/delete/book, Response state: OK, Request time: " + requestTime + "ms");
    }

}











