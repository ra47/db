package com.alli.backend.controllers;

import com.alli.backend.models.Role;
import com.alli.backend.models.User;
import com.alli.backend.repositories.UserRepository;
import com.alli.backend.services.UserService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    //base_url is localhost:8080

    // get all users
    // example: {{base_url}}
    //return users List
    @GetMapping("/")
    public List<User> getUsers()  {
        return userService.getUsers();
    }

    // get user by id
    // example: {{base_url}}/user/user1
    // return 200 and user1 object if user1 existed
    // return 404 not found if user1 not existed
    @GetMapping("/user/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username){
        try{
            User user = userService.findByUsername(username);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // get user by role (AGENT, USER) **UPPERCASE**
    // example: {{base_url}}/AGENT
    //return users List
    @GetMapping("/{role}")
    public List<User> getUsersByRole(@PathVariable("role") Role role){
        return userService.findByRole(role);
    }

}
