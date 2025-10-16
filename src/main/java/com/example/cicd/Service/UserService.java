package com.example.cicd.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cicd.dto.UserDto;
import com.example.cicd.dto.UserDto.CreateUpdateUserResponse;
import com.example.cicd.dto.UserDto.CreateUserRequest;
import com.example.cicd.dto.UserDto.GetUserNameResponse;
import com.example.cicd.dto.UserDto.GetUserResponse;
import com.example.cicd.dto.UserDto.UpdateUserRequest;
import com.example.cicd.entity.User;
import com.example.cicd.exception.NotFoundException;
import com.example.cicd.repository.UserRepository;
import com.example.cicd.utils.PasswordUtil;
import com.example.cicd.utils.StringUtil;

import io.micrometer.common.util.StringUtils;

interface InnerUserService {
    GetUserNameResponse getUsername(long id) throws Exception;
    GetUserResponse getUserInfo(long id);
    CreateUpdateUserResponse createUser(UserDto.CreateUserRequest request);
    CreateUpdateUserResponse updateUser(long id, UserDto.UpdateUserRequest request);
}

@Service
public class UserService implements InnerUserService {

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetUserNameResponse getUsername(long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("NOT_FOUND", "User not found"));
        return new GetUserNameResponse(user.getUsername());
    }

    @Override
    public GetUserResponse getUserInfo(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("NOT_FOUND", "User not found"));
        
            return new GetUserResponse(
            user.getId(), 
            user.getUsername(), 
            user.getFullName(), 
            user.getEmail(), 
            user.getMobileNo(), 
            user.isActive() ? "Active" : "Inactive");
    }

    @Override
    public CreateUpdateUserResponse createUser(CreateUserRequest request) {
        boolean isCreated = verifyIsCreated(request);
        if (!isCreated) {
            throw new IllegalArgumentException("User creation failed");
        }

        User user = new User();
        user.setUsername(request.username());
        String hashedPassword = PasswordUtil.hashPassword(request.password());
        user.setPassword(hashedPassword);
        user.setRealPassword(request.password());
        user.setFullName(request.fullname());
        user.setEmail(request.email());
        user.setMobileNo(request.mobileNo());
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user = userRepository.save(user);
        return new CreateUpdateUserResponse(user.getId(), user.getUsername());
    }

    @Override
    public CreateUpdateUserResponse updateUser(long id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("NOT_FOUND", "User not found"));

        boolean existsByEmail = userRepository.existsByEmail(request.email());
        if (existsByEmail) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (StringUtils.isNotBlank(request.email())) {
            user.setEmail(request.email());
        }
        if (StringUtils.isNotBlank(request.fullName())) {
            user.setFullName(request.fullName());
        }
        if (StringUtils.isNotBlank(request.mobileNo())) {
            user.setMobileNo(request.mobileNo());
        }
        user.setUpdatedAt(LocalDateTime.now());
        user = userRepository.save(user);

        return new CreateUpdateUserResponse(user.getId(), user.getUsername());
    }


    private boolean verifyIsCreated(CreateUserRequest request) {
        if (StringUtil.isValidUsername(request.username())) {
            System.out.println("Invalid username");
            return false;
        }

        if (StringUtil.isValidPassword(request.password())) {
            System.out.println("Invalid password");
            return false;
        }

        if (StringUtil.isValidEmail(request.email())) {
            System.out.println("Invalid email");
            return false;
        }

        if (StringUtils.isNotBlank(request.mobileNo()) 
            && StringUtil.isValidMobileNo(request.mobileNo())) {
            System.out.println("Invalid mobile no");
            return false;
        }

        boolean isDuplicate = userRepository.existsByUsername(request.username()) || userRepository.existsByEmail(request.email());
        if (isDuplicate) {
            System.out.println("Username or email already exists");
            return false;
        }

        System.out.println("Verify user to create success");
        return true;
    }
    
}
