package com.alli.backend.controllers;

import com.alli.backend.models.User;
import com.alli.backend.repositories.UserRepository;
import com.alli.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getUsers(){
        return (List<User>) userRepo.findAll();
    }

    @PostMapping("/register")
    public String register(@RequestBody User user)  {
        try{
            userService.register(user);
            return "Success";
        }catch (Exception e){
            return "User Existed";
        }
    }
}
