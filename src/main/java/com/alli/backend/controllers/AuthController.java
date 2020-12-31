package com.alli.backend.controllers;

import com.alli.backend.models.User;
import com.alli.backend.payload.JwtResponse;
import com.alli.backend.payload.LoginRequest;
import com.alli.backend.services.UserService;
import com.alli.backend.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    // check username duplicate
    // example: {{base_url}}/auth/validate-user
    // Expected Body: { "username" : "user1"}
    // return 200 if username is not duplicated
    // return 400 if username is duplicated
    @PostMapping("/validate-user")
    public ResponseEntity<?> checkDuplicateUsername(@RequestBody User user){
        return userService.checkUsername(user.getUsername()) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    // check email duplicate
    // example: {{base_url}}/auth/validate-email
    // Expected Body: { "email" : "test1@hotmail.com"}
    // return 200 if email is not duplicated
    // return 400 if email is duplicated
    @PostMapping("/validate-email")
    public ResponseEntity<?> checkDuplicateEmail(@RequestBody User user){
        return userService.checkEmail(user.getEmail()) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    // check citizen id duplicate
    // example: {{base_url}}/auth/validate-citizen-id
    // Expected Body: { "citizenId" : "1234567890123"}
    // return 200 if citizen id is not duplicated
    // return 400 if citizen id is duplicated
    @PostMapping("/validate-citizen-id")
    public ResponseEntity<?> checkDuplicateCitizenId(@RequestBody User user){
        return userService.checkCitizenId(user.getCitizenId()) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    // REGISTER
    // example: {{base_url}}/auth/register
    // Expected Body: User
    // return 200 if success
    // return 400 if duplicated
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user)  {
        try{
            userService.register(user);
            return ResponseEntity.ok("success");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // LOGIN
    // example: {{base_url}}/auth/login
    // Expected Body: { "username" : "user1", "password":"12345678"}
    // return 200 if success
    // return 400 if failed
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            JwtResponse jwtResponse = userService.login(loginRequest);
            return ResponseEntity.ok().body(jwtResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
