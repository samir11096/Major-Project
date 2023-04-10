package com.example.springbootlibrary.controller;

import com.example.springbootlibrary.entity.Book;
import com.example.springbootlibrary.service.BookService;
import com.example.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestHeader(value ="Authorization") String token ,@RequestParam Long bookId) throws Exception {
        String userEmail ="testuser@email.com";
        return bookService.checkoutBook(userEmail ,bookId);
    }

    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestHeader(value ="Authorization") String token ,@RequestParam Long bookId){
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        return bookService.checkoutBookByUser(userEmail,bookId);

    }

    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(@RequestHeader(value ="Authorization") String token){
        String userEmail = "testuser@email.com";
        return bookService.currentLoansCount(userEmail);
    }
}
