package com.example.cicd.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="username", unique=true, nullable=false, length=50)
    private String username;
    @Column(name="password", nullable=false, length=500)
    private String password;
    @Column(name="real_password", length=100)
    private String realPassword;
    @Column(name="email", unique=true, nullable=false, length=100)
    private String email;
    @Column(name="mobile_no", length=30)
    private String mobileNo;
    @Column(name="full_name", length=200)
    private String fullName;
    @Column(name="is_active", nullable=false, columnDefinition = "boolean default true")
    private boolean isActive;
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;
    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt;
}
