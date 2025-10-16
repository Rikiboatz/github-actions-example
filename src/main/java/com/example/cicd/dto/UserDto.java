package com.example.cicd.dto;

import com.example.cicd.entity.User;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {
    
    // ------------------------------------------ //
    // ============== Request DTO =============== //
    // ------------------------------------------ //
    public record CreateUserRequest(
        @NotBlank(message = "username is required")
        @Size(min = 3, max = 50, message = "username must be between 3 and 50 characters")
        @Nonnull String username,

        @NotBlank(message = "password is required")
        @Size(min = 6, max = 100, message = "password must be between 6 and 100 characters")
        @Nonnull String password,

        @NotBlank(message = "email is required")
        @Email(message = "email is invalid")
        @Size(max = 100, message = "email must be at most 100 characters")
        String email,

        String mobileNo,
        String fullname
    ) {}

    public record UpdateUserRequest(String fullName, String email, String mobileNo) {}



    // ------------------------------------------ //
    // ============== Response DTO ============== //
    // ------------------------------------------ //
    public record GetUserNameResponse(String username) {}

    public record GetUserResponse(Long id, String username, String fullname, String email, String mobileNo, String status) {}

    public record CreateUpdateUserResponse(Long id, String username) {}

}
