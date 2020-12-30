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

//    @Autowired
//    private UserRepository userRepo;

    @Autowired
    private UserService userService;

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

    // check username duplicate
    // example: {{base_url}}/validate-user
    // Expected Body: { "username" : "user1"}
    // return 200 if username is not duplicated
    // return 400 if username is duplicated
    @PostMapping("/validate-user")
    public ResponseEntity<?> checkDuplicateUsername(@RequestBody User user){
        return userService.checkUsername(user.getUsername()) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    // check email duplicate
    // example: {{base_url}}/validate-email
    // Expected Body: { "email" : "test1@hotmail.com"}
    // return 200 if email is not duplicated
    // return 400 if email is duplicated
    @PostMapping("/validate-email")
    public ResponseEntity<?> checkDuplicateEmail(@RequestBody User user){
        return userService.checkEmail(user.getEmail()) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    // check citizen id duplicate
    // example: {{base_url}}/validate-citizen-id
    // Expected Body: { "citizenId" : "1234567890123"}
    // return 200 if citizen id is not duplicated
    // return 400 if citizen id is duplicated
    @PostMapping("/validate-citizen-id")
    public ResponseEntity<?> checkDuplicateCitizenId(@RequestBody User user){
        return userService.checkCitizenId(user.getCitizenId()) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    //========BELOW THIS WAIT FOR IMPLEMENTATION========

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user)  {
        try{
            userService.register(user);
            return ResponseEntity.ok("success");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String email,@RequestBody String password){
        return ResponseEntity.ok().build();
    }


}
