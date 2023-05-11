package com.example.springbootlibrary;


import com.example.springbootlibrary.entity.Message;
import com.example.springbootlibrary.requestmodels.AdminQuestionRequest;
import com.example.springbootlibrary.service.MessagesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessagesService messagesService;


    @Value("${test.token}")
    private String token;


    @Value("${test.admin.token}")
    private String A_token;




    @Test
    public void postMessageShouldReturnOk() throws Exception {
        // Arrange
        Message message = new Message("Test Title","Testing things.!");

        // Act & Assert
        mockMvc.perform(post("/api/messages/secure/add/message")
                        .header("Authorization", "Bearer " + token)
                        .content(asJsonString(message))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        Mockito.verify(messagesService, times(1)).postMessage(message, "testuser@email.com");
    }


    @Test
    public void putMessageAsAdminShouldReturnOk() throws Exception {
        // Arrange
        AdminQuestionRequest adminQuestionRequest = new AdminQuestionRequest();

        // Act & Assert
        mockMvc.perform(put("/api/messages/secure/admin/message")
                        .header("Authorization", "Bearer " + A_token)
                        .content(asJsonString(adminQuestionRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(messagesService, times(1)).putMessage(adminQuestionRequest, "adminuser@email.com");
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}
