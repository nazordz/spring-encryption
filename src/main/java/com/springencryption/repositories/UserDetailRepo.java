package com.springencryption.repositories;

import com.springencryption.entities.UserDetail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepo extends JpaRepository<UserDetail, String> {
    
}
