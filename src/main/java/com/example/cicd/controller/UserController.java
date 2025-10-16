package com.example.cicd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cicd.Service.UserService;
import com.example.cicd.dto.UserDto.CreateUserRequest;
import com.example.cicd.dto.UserDto.UpdateUserRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired 
    private UserService service;
    
    @GetMapping("/getusername/{id}")
    public ResponseEntity<?> getUsername(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(service.getUsername(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id) {
        return ResponseEntity.ok().body(service.getUserInfo(id));
    }

    @PostMapping("/createduser")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequest request) {  
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(request));
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok().body(service.updateUser(Long.parseLong(id), request));
    }
}
