package com.example.springbootlibrary;

import com.example.springbootlibrary.entity.Book;
import com.example.springbootlibrary.responsemodels.ShelfCurrentLoansResponse;
import com.example.springbootlibrary.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import java.util.List;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;


    @Value("${test.token}")
    private String oktaJwtToken;

    @Test
    @DisplayName("Test currentLoans endpoint")
    void testCurrentLoansEndpoint() throws Exception {
        // Arrange
        List<ShelfCurrentLoansResponse> expectedResponse = new ArrayList<>();
        // add expected response data

        when(bookService.currentLoans(anyString())).thenReturn(expectedResponse);



        // Act
        mockMvc.perform(get("/api/books/secure/currentloans")
                        .header("Authorization", "Bearer "+oktaJwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
//        mockMvc.perform(get("/api/books/secure/currentloans")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized());
//
//        // Assert
        verify(bookService, times(1)).currentLoans(anyString());
//        verify(bookService, never()).currentLoans(anyString());
    }

    @Test
    @DisplayName("Test checkoutBook endpoint")
    void testCheckoutBookEndpoint() throws Exception {
        // Arrange
        long bookId = 12345L;
        Book expectedResponse = new Book();
        // add expected response data
        when(bookService.checkoutBook(anyString(), eq(bookId))).thenReturn(expectedResponse);

        // Act
        mockMvc.perform(put("/api/books/secure/checkout?bookId=" + bookId)
                        .header("Authorization", "Bearer " + oktaJwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(bookService, times(1)).checkoutBook(anyString(), eq(bookId));

//        mockMvc.perform(put("/api/books/secure/checkout?bookId=" + bookId)
//                        .header("Authorization", "Bearer " + oktaJwtToken)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized());
//
//        // Assert
//        verify(bookService, never()).checkoutBook(anyString(), eq(bookId));
    }

    @Test
    @DisplayName("Test checkoutBookByUser endpoint")
    void testCheckoutBookByUserEndpoint() throws Exception {
        // Arrange
        long bookId = 12345L;
        boolean expectedResponse = true;
        when(bookService.checkoutBookByUser(anyString(), eq(bookId))).thenReturn(expectedResponse);

        // Act
        mockMvc.perform(get("/api/books/secure/ischeckedout/byuser?bookId=" + bookId)
                        .header("Authorization", "Bearer " + oktaJwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(bookService, times(1)).checkoutBookByUser(anyString(), eq(bookId));
    }

    @Test
    @DisplayName("Test currentLoansCount endpoint")
    void testCurrentLoansCountEndpoint() throws Exception {
        // Arrange
        int expectedResponse = 5;
        when(bookService.currentLoansCount(anyString())).thenReturn(expectedResponse);

        // Act
        mockMvc.perform(get("/api/books/secure/currentloans/count")
                        .header("Authorization", "Bearer " + oktaJwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(bookService, times(1)).currentLoansCount(anyString());
    }

    @Test
    @DisplayName("Test returnBook endpoint")
    void testReturnBookEndpoint() throws Exception {
        // Arrange
        long bookId = 12345L;
        doNothing().when(bookService).returnBook(anyString(), eq(bookId));

        // Act
        mockMvc.perform(put("/api/books/secure/return?bookId=" + bookId)
                        .header("Authorization", "Bearer " + oktaJwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(bookService, times(1)).returnBook(anyString(), eq(bookId));
    }

    @Test
    @DisplayName("Test renewLoan endpoint")
    void testRenewLoanEndpoint() throws Exception {
        // Arrange
        long bookId = 12345L;
        doNothing().when(bookService).renewLoan(anyString(), eq(bookId));

        // Act
        mockMvc.perform(put("/api/books/secure/renew/loan?bookId=" + bookId)
                        .header("Authorization", "Bearer " + oktaJwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(bookService, times(1)).renewLoan(anyString(), eq(bookId));
    }

}