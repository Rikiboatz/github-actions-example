package com.example.cicd.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cicd.Service.UserService;
import com.example.cicd.dto.UserDto.GetUserNameResponse;
import com.example.cicd.entity.User;
import com.example.cicd.exception.NotFoundException;
import com.example.cicd.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;
    
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        // MockitoAnnotations.initMocks(this); 
        // userService = new UserService();
        // userService.setUserRepository(userRepository);
    }

    @Test
    public void test_success_getusername_whenHasUser_shouldSuccess() throws Exception {
        // Arrange
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockUserDto()));
        
        // Act
        GetUserNameResponse response = userService.getUsername(1);

        // Assert
        Assertions.assertEquals(response.username(), "username");

        // Verify: ยืนยันว่าเรียกด้วยพารามิเตอร์ที่คาดไว้
        Mockito.verify(userRepository).findById(1L);
    }

    @Test
    public void test_failed_getusername_whenUserIsNull_shouldThrowNotFound() throws Exception {
        // Arrange
        Mockito.when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        NotFoundException ex = assertThrows(NotFoundException.class, 
            () -> userService.getUsername(99));

        // Assert
        Assertions.assertEquals("User not found", ex.getMessage());
        Assertions.assertEquals("NOT_FOUND", ex.getErrorCode());

        // Verify
        Mockito.verify(userRepository).findById(99L);
        Mockito.verifyNoMoreInteractions(userRepository);

    }

    private User mockUserDto() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setFullName("");
        user.setEmail("test@gmail.com");
        user.setMobileNo("");
        user.setActive(true);

        return user;
    }
}
