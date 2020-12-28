package com.alli.backend.controllers;

import com.alli.backend.models.Role;
import com.alli.backend.models.User;
import com.alli.backend.repositories.UserRepository;
import com.alli.backend.services.UserService;
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

    @GetMapping("/")
    public List<User> getUsers()  {
        return userService.getUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user)  {
        try{
            userService.register(user);
            return ResponseEntity.ok("success");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username){
        try{
            User user = userService.findByUsername(username);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable("role") Role role){
        return userService.findByRole(role);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String email,@RequestBody String password){
        return ResponseEntity.ok().build();
    }
}
