package com.example.springbootlibrary;

import com.example.springbootlibrary.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.springbootlibrary.requestmodels.AddBookRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AdminService adminService;

    @Value("${test.admin.token}")
    private String token;



    @Test
    @DisplayName("Test increaseBookQuantity endpoint")
    void testIncreaseBookQuantityEndpoint() throws Exception {
        // Arrange
        Long bookId = 1L;

        // Act
        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/secure/increase/book/quantity")
                        .header("Authorization", "Bearer "+token)
                        .param("bookId", bookId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        // Assert
        Mockito.verify(adminService, Mockito.times(1)).increaseBookQuantity(bookId);
    }

    @Test
    @DisplayName("Test decreaseBookQuantity endpoint")
    void testDecreaseBookQuantityEndpoint() throws Exception {
        // Arrange
        Long bookId = 1L;

        // Act
        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/secure/decrease/book/quantity")
                        .header("Authorization", "Bearer "+token)
                        .param("bookId", bookId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        Mockito.verify(adminService, Mockito.times(1)).decreaseBookQuantity(bookId);
    }

//    @Test
//    @DisplayName("Test postBook endpoint")
//    void testPostBookEndpoint() throws Exception {
//        // Arrange
//        AddBookRequest addBookRequest = new AddBookRequest();
//        addBookRequest.setTitle("Test Book");
//        addBookRequest.setAuthor("Test Author");
//        addBookRequest.setDescription("Test Description");
//        addBookRequest.setCopies(123);
//        addBookRequest.setCategory("Test Category");
//        addBookRequest.setImg(null);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // Act
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/secure/add/book")
//                        .header("Authorization", token)
//                        .content(objectMapper.writeValueAsString(addBookRequest))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        // Assert
//        Mockito.verify(adminService, Mockito.times(1)).postBook(addBookRequest);
//    }

    @Test
    @DisplayName("Test deleteBook endpoint")
    void testDeleteBookEndpoint() throws Exception {
        // Arrange
        Long bookId = 1L;

        // Act
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/admin/secure/delete/book")
                        .header("Authorization", "Bearer "+token)
                        .param("bookId", bookId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        Mockito.verify(adminService, Mockito.times(1)).deleteBook(bookId);
    }
}
