package com.springencryption.controllers;

import com.springencryption.entities.UserDetail;
import com.springencryption.repositories.UserDetailRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserDetailController {

    @Autowired
    private UserDetailRepo userDetailRepo;

    @PostMapping
    public UserDetail create(@RequestBody UserDetail user) {
        return userDetailRepo.save(user);
    }

    @GetMapping
    public Iterable<UserDetail> findAll() {
        return userDetailRepo.findAll();
    }
}
