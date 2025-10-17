package com.example.cicd.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cicd.entity.User;
import com.example.cicd.repository.UserRepository;

// ทดสอบ Spring Boot (รันเซิร์ฟเวอร์ในเทสเอง)
// โดยใช้ Mock Repository (ไม่แตะ DB จริง)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // สั่งให้สร้าง MockMvc มาให้
@EnableAutoConfiguration // สั่งให้ Spring Boot auto config ทุกอย่าง
public class UserIntegrationTest {
    
    @Autowired MockMvc mockMvc; // <-- ตัวช่วยเรียก endpoint จริง

    @MockBean UserRepository userRepository; // <-- mock repo เพื่อไม่แตะ DB

    @Test
    void getUsername_shouldReturn200_andBody() throws Exception {
        // Arrange: ให้ repo.findById(1) คืน User จำลอง
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(mockUserDto()));

        // Act + Assert: เรียก endpoint จริง (service จริง, controller จริง)
        mockMvc.perform(get("/api/users/getusername/{id}", 1).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("Rikiboat"));

        // Verify repo ถูกเรียกจริง
        Mockito.verify(userRepository).findById(1L);
    }

    @Test
    void getUsername_notFound_shouldReturn500() throws Exception {
        // Arrange: ให้ repo.findById(1) คืน null (ไม่เจอ)
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act + Assert: เรียก endpoint จริง (service จริง, controller จริง)
        mockMvc.perform(get("/api/users/getusername/{id}", 10).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value("User not found"));


        // Verify repo ถูกเรียกจริง
        Mockito.verify(userRepository).findById(10L);
    }

    @Test
    void getUser_shouldReturn200_andBody() throws Exception {
        // Arrange: ให้ repo.findById(1) คืน User จำลอง
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(mockUserDto()));

        // Act + Assert: เรียก endpoint จริง (service จริง, controller จริง)
        mockMvc.perform(get("/api/users/{id}", 1).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.username").value("Rikiboat"))
            .andExpect(jsonPath("$.fullname").value("fullname lastname"))
            .andExpect(jsonPath("$.email").value("rikiboat@gmail.com"))
            .andExpect(jsonPath("$.mobileNo").value("08xxxxxxxx"))
            .andExpect(jsonPath("$.status").value("Active"));

        // Verify repo ถูกเรียกจริง
        Mockito.verify(userRepository).findById(1L);
    }

    @Test
    void getUser_notFound_shouldReturn500() throws Exception {
        // Arrange: ให้ repo.findById(1) คืน null (ไม่เจอ)
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act + Assert: เรียก endpoint จริง (service จริง, controller จริง)
        mockMvc.perform(get("/api/users/{id}", 10).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value("User not found"));

        // Verify repo ถูกเรียกจริง
        Mockito.verify(userRepository).findById(10L);
    }

    private User mockUserDto() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Rikiboat");
        user.setEmail("rikiboat@gmail.com");
        user.setMobileNo("08xxxxxxxx");
        user.setFullName("fullname lastname");
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }

}
