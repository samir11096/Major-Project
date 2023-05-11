package com.example.springbootlibrary.controller;

import com.example.springbootlibrary.entity.Book;
import com.example.springbootlibrary.responsemodels.ShelfCurrentLoansResponse;
import com.example.springbootlibrary.service.BookService;
import com.example.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);


    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

//    @GetMapping("/secure/currentloans")
//    public List<ShelfCurrentLoansResponse> currentLoans(@RequestHeader(value ="Authorization") String token) throws Exception{
//        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
//        logger.info("Received request: GET /api/books/secure/currentloans for user {}", userEmail);
//        return bookService.currentLoans(userEmail);
//    }
    @GetMapping("/secure/currentloans")
    public List<ShelfCurrentLoansResponse> currentLoans(@RequestHeader(value ="Authorization") String token) throws Exception{
        long startTime = System.currentTimeMillis(); // record start time
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        List<ShelfCurrentLoansResponse> response = bookService.currentLoans(userEmail);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time

        // log API path, response state, and request time
        if(response != null) {
            logger.info("API: /api/books/secure/currentloans, Response state: OK, Request time: " + requestTime + "ms");
        } else {
            logger.info("API: /api/books/secure/currentloans, Response state: ERROR, Request time: " + requestTime + "ms");
        }
        return response;
    }


        @PutMapping("/secure/checkout")
        public Book checkoutBook(@RequestHeader(value ="Authorization") String token ,@RequestParam Long bookId) throws Exception {
            long startTime = System.currentTimeMillis(); // record start time
            String userEmail ="testuser@email.com";
            Book book =  bookService.checkoutBook(userEmail ,bookId);
            long endTime = System.currentTimeMillis(); // record end time
            long requestTime = endTime - startTime; // calculate request time
            // log API path, response state, and request time
            if(book != null) {
                logger.info("API: /api/books/secure/checkout, Response state: OK, Request time: " + requestTime + "ms");
            } else {
                logger.info("API: /api/books/secure/checkout, Response state: ERROR, Request time: " + requestTime + "ms");
            }
            return book;
        }

    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestHeader(value ="Authorization") String token ,@RequestParam Long bookId){
        long startTime = System.currentTimeMillis(); // record start time
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
//        return bookService.checkoutBookByUser(userEmail,bookId);
        Boolean isCheckedOut = bookService.checkoutBookByUser(userEmail, bookId);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time

        // log API path, response state, and request time
        if (isCheckedOut != null) {
            logger.info("API: /api/books/secure/ischeckedout/byuser, Response state: OK, Request time: " + requestTime + "ms");
        } else {
            logger.info("API: /api/books/secure/ischeckedout/byuser, Response state: ERROR, Request time: " + requestTime + "ms");
        }
        return isCheckedOut;

    }

    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(@RequestHeader(value ="Authorization") String token){
//        String userEmail = "testuser@email.com";
//        logger.info("Received request: GET /api/books/secure/currentloans/count");
//        return bookService.currentLoansCount(userEmail);
        long startTime = System.currentTimeMillis(); // record start time
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        int count = bookService.currentLoansCount(userEmail);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time

        // log API path, response state, and request time
        if(count != -1) {
            logger.info("API: /api/books/secure/currentloans/count, Response state: OK, Request time: " + requestTime + "ms");
        } else {
            logger.info("API: /api/books/secure/currentloans/count, Response state: ERROR, Request time: " + requestTime + "ms");
        }
        return count;
    }

    @PutMapping("/secure/return")
    public void returnBook(@RequestHeader(value="Authorization") String token,
                           @RequestParam Long bookId) throws Exception{
//        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
//        logger.info("Received request: PUT /api/books/secure/return for book ID {}", bookId);
//        bookService.returnBook(userEmail,bookId);

        long startTime = System.currentTimeMillis(); // record start time
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        bookService.returnBook(userEmail, bookId);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time
        logger.info("API: /api/books/secure/return, Response state: OK, Request time: " + requestTime + "ms");

    }
    @PutMapping("/secure/renew/loan")
    public void renewLoan(@RequestHeader(value = "Authorization") String token,
                          @RequestParam Long bookId) throws Exception {
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
//        logger.info("Received request: PUT /api/books/secure/renew/loan for book ID {}", bookId);
//        bookService.renewLoan(userEmail, bookId);

        long startTime = System.currentTimeMillis(); // record start time
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        bookService.renewLoan(userEmail, bookId);
        long endTime = System.currentTimeMillis(); // record end time
        long requestTime = endTime - startTime; // calculate request time
        logger.info("API: /api/books/secure/renew/loan, Response state: OK, Request time: " + requestTime + "ms");
    }
}
