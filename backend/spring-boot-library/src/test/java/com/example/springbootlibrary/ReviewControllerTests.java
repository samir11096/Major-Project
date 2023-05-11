package com.example.springbootlibrary;

import com.example.springbootlibrary.requestmodels.ReviewRequest;
import com.example.springbootlibrary.service.BookService;
import com.example.springbootlibrary.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.mockito.Mockito.*;



@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;


    @Value("${test.token}")
    private String oktaJwtToken;

//    @Test
//    @DisplayName("Test postReview endpoint")
//    void testPostReviewEndpoint() throws Exception {
//        // Arrange
//        ReviewRequest reviewRequest = new ReviewRequest();
//        reviewRequest.setBookId(1L);
//        reviewRequest.setRating(4);
//        reviewRequest.setReviewDescription(Optional.ofNullable(null));
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // Act
//        mockMvc.perform(post("/api/reviews/secure")
//                        .header("Authorization", "Bearer "+oktaJwtToken)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(reviewRequest)))
//                .andExpect(status().isOk());
//
//        // Assert
//        verify(reviewService, times(1)).postReview("testuser@email.com", reviewRequest);
//    }

    @Test
    @DisplayName("Test reviewBookByUser endpoint")
    void testReviewBookByUserEndpoint() throws Exception {
        // Arrange
        Long bookId = 1L;

        when(reviewService.userReviewListed(anyString(), eq(bookId))).thenReturn(true);

        // Act
        mockMvc.perform(get("/api/reviews/secure/user/book?bookId=" + bookId)
                        .header("Authorization", "Bearer "+oktaJwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        // Assert
        verify(reviewService, times(1)).userReviewListed(anyString(), eq(bookId));
    }

}
